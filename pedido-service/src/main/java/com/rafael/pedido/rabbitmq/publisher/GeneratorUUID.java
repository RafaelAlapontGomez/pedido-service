package com.rafael.pedido.rabbitmq.publisher;

import java.util.UUID;

import org.springframework.stereotype.Component;

@Component
public class GeneratorUUID {

	public UUID generaterUUID() {
		return UUID.randomUUID();
	}
}
