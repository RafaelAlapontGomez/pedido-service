package com.rafael.pedido.rabbitmq.messages;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @NoArgsConstructor @Builder
public class PedidoStockMessage {
	private String uuid;
	private Long pedidoId;
	private Long customerId;
	private List<ItemMessage> items;
	
    public PedidoStockMessage(@JsonProperty("uuid") String uuid,
            			 	  @JsonProperty("pedidoId") Long pedidoId,
            			 	  @JsonProperty("customerId") Long customerId,
            			 	  @JsonProperty("items") List<ItemMessage> items) {

    	this.uuid = uuid;
    	this.pedidoId = pedidoId; 
    	this.customerId = customerId;
    	this.items = items;
    }
}
