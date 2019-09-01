package com.rafael.pedido.rabbitmq.publisher;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.rafael.pedido.rabbitmq.messages.PedidoStockMessage;

@Component
public class StockPublisher {
	   @Autowired
	   private AmqpTemplate amqpTemplate;
	   
	   @Value("${jsa.rabbitmq.exchange}")
	   private String exchange;
	   
	   @Value("${jsa.rabbitmq.routingkey}")
	   private String routingKey;
	   
	   public void produceMsg(PedidoStockMessage pedidoStockMessage){
	      amqpTemplate.convertAndSend(exchange, routingKey, pedidoStockMessage);
	      System.out.println("Send msg = " + pedidoStockMessage);
	   }
}
