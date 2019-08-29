package com.rafael.pedido.mongodb.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.rafael.pedido.mongodb.domain.Pedido;
import com.rafael.pedido.mongodb.domain.State;

public interface PedidoRepository extends MongoRepository<Pedido, Long> {
	List<Pedido> findByState(State state);
	List<Pedido> findByCustomerId(Long customerId);

}
