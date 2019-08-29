package com.rafael.pedido.mappings;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.dozer.DozerBeanMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.rafael.pedido.dtos.ItemDto;
import com.rafael.pedido.dtos.PedidoDto;
import com.rafael.pedido.mongodb.domain.Item;
import com.rafael.pedido.mongodb.domain.Pedido;
import com.rafael.pedido.mongodb.domain.State;

@SpringBootTest
public class PedidoMappingTest {
	@Autowired
	DozerBeanMapper mapper;
	
    @DisplayName("Test Convert entity to Dto")
    @Test
    public void convertEntityToDto() {
		PedidoDto pedidoDto = mapper.map(buildPedido(), PedidoDto.class);
    	
    	Assertions.assertAll("pedido Dto", 
    			() -> Assertions.assertEquals(new Long(1), pedidoDto.getId()),
    			() -> Assertions.assertEquals(new Long(1), pedidoDto.getCustomerId()),
    			() -> Assertions.assertEquals(new Double(200.00), pedidoDto.getTotal()),
    			() -> Assertions.assertEquals(3, pedidoDto.getItems().size()),
    			() -> Assertions.assertEquals("Pendiente", pedidoDto.getState()));

    }
    
    @DisplayName("Test Convert Dto to entity")
    @Test
    public void ConvertDtoToEntity() {
		Pedido pedido = mapper.map(buildPedidoDto(), Pedido.class);

    	Assertions.assertAll("pedido Dto", 
    			() -> Assertions.assertEquals(new Long(1), pedido.getId()),
    			() -> Assertions.assertEquals(new Long(1), pedido.getCustomerId()),
    			() -> Assertions.assertEquals(new Double(200.00), pedido.getTotal()),
    			() -> Assertions.assertEquals(3, pedido.getItems().size()),
    			() -> Assertions.assertEquals(State.Pendiente, pedido.getState()));
    }

	private Pedido buildPedido() {
    	Item item1 = Item.builder().itemId(1L).productId(1L).qty(30L).build();
    	Item item2 = Item.builder().itemId(2L).productId(2L).qty(40L).build();
    	Item item3 = Item.builder().itemId(3L).productId(3L).qty(50L).build();
    	List<Item> items = Arrays.asList(item1, item2, item3);
    	
    	Pedido pedido = 
    		Pedido.builder()
    			.id(1L)
    			.customerId(1L)
    			.total(200.0)
    			.fecha(new Date())
    			.state(State.Pendiente)
    			.items(items)
    			.build();
    	return pedido;		
    }

    private PedidoDto buildPedidoDto() {
    	ItemDto item1 = ItemDto.builder().itemId(1L).productId(1L).qty(30L).build();
    	ItemDto item2 = ItemDto.builder().itemId(2L).productId(2L).qty(40L).build();
    	ItemDto item3 = ItemDto.builder().itemId(3L).productId(3L).qty(50L).build();
    	List<ItemDto> items = Arrays.asList(item1, item2, item3);
    	
    	PedidoDto pedidoDto = 
    		PedidoDto.builder()
    			.id(1L)
    			.customerId(1L)
    			.total(200.0)
    			.fecha(new Date())
    			.items(items)
    			.state("Pendiente")
    			.build();
    	return pedidoDto;		
    }

}
