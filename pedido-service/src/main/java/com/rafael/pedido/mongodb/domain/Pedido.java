package com.rafael.pedido.mongodb.domain;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.validation.constraints.NotNull;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@SuppressWarnings("serial")
@Data @NoArgsConstructor @AllArgsConstructor @Builder
@Document(collection = "pedido")
public class Pedido implements Serializable {
	
	@Id
    private Long id;
	
    @NotNull
    private Long customerId;

    @NotNull
    private Date fecha;
    
    @NotNull
    private State state;

    private Double total;
    
    @NotNull
    List<Item> items;

}
