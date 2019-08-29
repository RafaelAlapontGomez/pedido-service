package com.rafael.pedido.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.rafael.pedido.dtos.PedidoDto;
import com.rafael.pedido.services.exceptions.NoDataFoundException;
import com.rafael.pedido.services.impl.PedidoQueryServiceImpl;

@RestController
@RequestMapping("/pedido")
public class PedidoQueryController {

	@Autowired
	PedidoQueryServiceImpl pedidoQueryService;
	
	@GetMapping
	ResponseEntity<List<PedidoDto>> obtenerPedidos() {
		return ResponseEntity.ok(pedidoQueryService.findAll());
	}
	
	@GetMapping("/{id}")
	ResponseEntity<PedidoDto> obtenerPedidosById(
			@PathVariable(name = "id") Long id) {
		PedidoDto pedidoDto = null;
		try {
			pedidoDto = pedidoQueryService.findById(id);
		} catch (NoDataFoundException ex) {
	         throw new ResponseStatusException(HttpStatus.NOT_FOUND, ex.getMessage(), ex);
	    }
		return ResponseEntity.ok(pedidoDto);
	}

	@GetMapping("/customer/{customerId}")
	ResponseEntity<List<PedidoDto>> obtenerPedidosByCustomerId(
			@PathVariable(name = "customerId") Long customerId) {
		return ResponseEntity.ok(pedidoQueryService.findByCustomerId(customerId));
	}
}
