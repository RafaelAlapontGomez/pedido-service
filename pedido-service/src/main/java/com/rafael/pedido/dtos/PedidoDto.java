package com.rafael.pedido.dtos;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@SuppressWarnings("serial")
@Data @NoArgsConstructor @AllArgsConstructor @Builder
public class PedidoDto implements Serializable {
	
    @NotNull
    private Long id;
	
    @NotNull
    private Long customerId;

    @NotNull
    private Date fecha;
    
    @NotNull
    private String state;

    private Double total;
    
    @NotNull
    List<ItemDto> items;
}
