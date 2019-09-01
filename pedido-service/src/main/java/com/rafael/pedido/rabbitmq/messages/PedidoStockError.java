package com.rafael.pedido.rabbitmq.messages;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @NoArgsConstructor @Builder
public class PedidoStockError {
	private String uuid;
	private Long pedidoId;
	private String causa;
	
    public PedidoStockError(@JsonProperty("uuid") String uuid,
		 	  				  @JsonProperty("pedidoId") Long pedidoId,
		 	  				  @JsonProperty("causa") String causa) {
		this.uuid = uuid;
		this.pedidoId = pedidoId;
		this.causa = causa;
	}

}
