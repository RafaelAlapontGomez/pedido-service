package com.rafael.pedido.rabbitmq.messages;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @NoArgsConstructor @Builder
public class ItemMessage {
    private Long productId;
    private Long qty;
    
    public ItemMessage(@JsonProperty("productId") Long productId,
    				   @JsonProperty("qty") Long qty) {
			this.productId = productId;
			this.qty = qty;
    }
}
