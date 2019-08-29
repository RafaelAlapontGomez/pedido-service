package com.rafael.pedido.mongodb.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.rafael.pedido.mongodb.domain.Pedido;

public interface PedidoRepository extends MongoRepository<Pedido, Long> {

}
