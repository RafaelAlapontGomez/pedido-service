package com.rafael.pedido.mongodb.repository;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.rafael.pedido.mongodb.domain.Item;
import com.rafael.pedido.mongodb.domain.Pedido;

@SpringBootTest
public class PedidoRepositoryTest {

	@Autowired
	PedidoRepository pedidoRepository;
	
    @DisplayName("Test Save customer")
    @Test
    public void createPedidoTest() {
    	@SuppressWarnings("unused")
		Pedido pedidoNew = pedidoRepository.save(buildPedido());
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
    			.total(0.0)
    			.fecha(new Date())
    			.items(items)
    			.build();
    	return pedido;		
    }

}
