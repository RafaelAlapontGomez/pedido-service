package com.rafael.pedido.mongodb.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.rafael.pedido.mongodb.domain.Counter;

public interface CounterRepository extends MongoRepository<Counter, String> {

}
