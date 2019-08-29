package com.rafael.pedido.services.impl;

import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rafael.pedido.dtos.PedidoDto;
import com.rafael.pedido.mongodb.domain.Pedido;
import com.rafael.pedido.mongodb.repository.PedidoRepository;
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
	
	@Override
	public PedidoDto createPedido(PedidoDto pedidoDto) {
		Pedido pedido = mapper.map(pedidoDto, Pedido.class);
		pedido.setId(counterService.nextValue());
		return mapper.map(pedidoRepository.save(pedido), PedidoDto.class);
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
}
