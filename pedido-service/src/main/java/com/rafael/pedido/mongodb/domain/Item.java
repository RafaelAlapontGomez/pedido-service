package com.rafael.pedido.mongodb.domain;

import java.io.Serializable;

import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@SuppressWarnings("serial")
@Data @NoArgsConstructor @AllArgsConstructor @Builder
public class Item implements Serializable {
	
	@NotNull
    private Long itemId;
	
	@NotNull
    private Long productId;
	
	@NotNull
    private Long qty;

}
