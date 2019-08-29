package com.rafael.pedido.services;

import java.util.List;

import com.rafael.pedido.dtos.PedidoDto;
import com.rafael.pedido.mongodb.domain.State;
import com.rafael.pedido.services.exceptions.NoDataFoundException;

public interface PedidoQueryService {
	
	List<PedidoDto> findAll();
	PedidoDto findById(Long id) throws NoDataFoundException;
	List<PedidoDto> findByState(State estado);
	List<PedidoDto> findByCustomerId(Long customerId);
	
	
}
