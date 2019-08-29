package com.rafael.pedido.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.rafael.pedido.dtos.PedidoDto;

@RequestMapping("/pedido")
public interface APIPedidoCommand {
	
	@PostMapping
	ResponseEntity<PedidoDto> createPedido(@RequestBody PedidoDto pedidoDto);
	
	@PutMapping("/{id}")
	public ResponseEntity<PedidoDto> updateCustomer(
			@PathVariable(name = "id") Long id,
			@RequestBody PedidoDto pedidoDto);
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteCustomer(@PathVariable(name = "id") Long id);	
	
}
