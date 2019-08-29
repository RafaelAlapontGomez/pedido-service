package com.rafael.pedido.services;

public interface CounterCommandService {
	
	static final String COUNTER_ID = "pedido";
	
	Long nextValue();
	Long beforeValue();
	Long currentValue();
}
