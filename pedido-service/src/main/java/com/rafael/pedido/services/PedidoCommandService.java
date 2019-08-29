package com.rafael.pedido.services;

import com.rafael.pedido.dtos.PedidoDto;
import com.rafael.pedido.services.exceptions.NoDataFoundException;

public interface PedidoCommandService {
	PedidoDto createPedido(PedidoDto pedidoDto);
	PedidoDto updatePedido(Long id, PedidoDto pedidoDto) throws NoDataFoundException;
	void deletePedido(Long id);
}
