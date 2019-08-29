package com.rafael.pedido.services.impl;

import java.util.ArrayList;
import java.util.List;

import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rafael.pedido.dtos.PedidoDto;
import com.rafael.pedido.mongodb.domain.Pedido;
import com.rafael.pedido.mongodb.domain.State;
import com.rafael.pedido.mongodb.repository.PedidoRepository;
import com.rafael.pedido.services.PedidoQueryService;
import com.rafael.pedido.services.exceptions.NoDataFoundException;

@Service
public class PedidoQueryServiceImpl implements PedidoQueryService {

	@Autowired
	PedidoRepository pedidoRepository;

	@Autowired
	DozerBeanMapper mapper;
	
	@Override
	public List<PedidoDto> findAll() {
		List<Pedido> pedidos = pedidoRepository.findAll();
		return mapToPedidosDto(pedidos);
	}

	@Override
	public PedidoDto findById(Long id) throws NoDataFoundException {
		Pedido pedido = pedidoRepository.findById(id)
							.orElseThrow(() -> new NoDataFoundException(String.format("Pedido %d no encontrado", id)));
		return mapper.map(pedido, PedidoDto.class);
	}

	@Override
	public List<PedidoDto> findByState(State estado) {
		List<Pedido> pedidos = pedidoRepository.findByState(estado);
		return mapToPedidosDto(pedidos);
	}
	
	@Override
	public List<PedidoDto> findByCustomerId(Long customerId) {
		List<Pedido> pedidos = pedidoRepository.findByCustomerId(customerId);
		return mapToPedidosDto(pedidos);
	}

	private List<PedidoDto> mapToPedidosDto(List<Pedido> pedidos) {
		List<PedidoDto> pedidosDto = new ArrayList<>();
		pedidos.forEach((item -> pedidosDto.add(mapper.map(item, PedidoDto.class))));
		return pedidosDto;
	}

	
}
