package com.rafael.pedido.services.impl;

import java.util.ArrayList;
import java.util.List;

import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rafael.pedido.dtos.ItemDto;
import com.rafael.pedido.dtos.PedidoDto;
import com.rafael.pedido.mongodb.domain.Pedido;
import com.rafael.pedido.mongodb.repository.PedidoRepository;
import com.rafael.pedido.rabbitmq.messages.ItemMessage;
import com.rafael.pedido.rabbitmq.messages.PedidoStockMessage;
import com.rafael.pedido.rabbitmq.publisher.GeneratorUUID;
import com.rafael.pedido.rabbitmq.publisher.StockPublisher;
import com.rafael.pedido.services.PedidoCommandService;
import com.rafael.pedido.services.exceptions.NoDataFoundException;

@Service
public class PedidoCommandServiceImpl implements PedidoCommandService {

	@Autowired
	PedidoRepository pedidoRepository;
	
	@Autowired
	DozerBeanMapper mapper;
	
	@Autowired
	CounterCommandServiceImpl counterService;
	
	@Autowired
	GeneratorUUID generatorUUID;
	
	@Autowired
	StockPublisher stockPublisher;
	
	@Override
	public PedidoDto createPedido(PedidoDto pedidoDto) {
		Pedido pedido = mapper.map(pedidoDto, Pedido.class);
		pedido.setId(counterService.nextValue());
		PedidoDto createdPedidoDto = mapper.map(pedidoRepository.save(pedido), PedidoDto.class);
		publishStockMessage(createdPedidoDto);
		return createdPedidoDto;
	}

	@Override
	public PedidoDto updatePedido(Long id, PedidoDto pedidoDto) throws NoDataFoundException {
		Pedido pedido = pedidoRepository.findById(id)
				.orElseThrow( () -> new NoDataFoundException(String.format("Pedido %s not found", id)));
		if (!pedido.getId().equals(pedidoDto.getId())) {
			throw new NoDataFoundException(String.format("Pedido %s not found", pedidoDto.getId()));
		}
		Pedido newPedido = mapper.map(pedidoDto, Pedido.class);
		Pedido upPedido = pedidoRepository.save(newPedido);
		return mapper.map(upPedido, PedidoDto.class);
	}

	@Override
	public void deletePedido(Long id) {
		pedidoRepository.deleteById(id);

	}

	private void publishStockMessage(PedidoDto createdPedidoDto) {
		String uuid = generatorUUID.generaterUUID().toString();
		PedidoStockMessage pedidoStockMessage =
				PedidoStockMessage.builder()
					.uuid(uuid)
					.pedidoId(createdPedidoDto.getId())
					.customerId(createdPedidoDto.getCustomerId())
					.items(buildItemMessage(createdPedidoDto.getItems()))
					.build();
		stockPublisher.produceMsg(pedidoStockMessage);
	}

	private List<ItemMessage> buildItemMessage(List<ItemDto> items) {
		List<ItemMessage> itemMessages = new ArrayList<>();
		items.forEach( (item) -> itemMessages.add(
				new ItemMessage(item.getProductId(), item.getQty())));
		return itemMessages;
	}

}
