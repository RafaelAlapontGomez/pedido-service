package com.rafael.pedido.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.rafael.pedido.dtos.PedidoDto;
import com.rafael.pedido.services.exceptions.NoDataFoundException;
import com.rafael.pedido.services.impl.PedidoCommandServiceImpl;

@RestController
public class PedidoCommandController implements APIPedidoCommand {

	@Autowired
	PedidoCommandServiceImpl pedidoService;
	
	@Override
	public ResponseEntity<PedidoDto> createPedido(PedidoDto pedidoDto) {
		return ResponseEntity.ok(pedidoService.createPedido(pedidoDto));
	}

	@Override
	public ResponseEntity<PedidoDto> updateCustomer(Long id, PedidoDto pedidoDto) {
		PedidoDto newPedidoDto;
		try {
			newPedidoDto = pedidoService.updatePedido(id, pedidoDto);
		} catch (NoDataFoundException ex) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, ex.getMessage(), ex);		}
		return ResponseEntity.ok(newPedidoDto);
	}

	@Override
	public ResponseEntity<Void> deleteCustomer(Long id) {
		pedidoService.deletePedido(id);
		return ResponseEntity.ok().build();
	}

}
