package com.rafael.pedido.mongodb.repository;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.rafael.pedido.mongodb.domain.Item;
import com.rafael.pedido.mongodb.domain.Pedido;
import com.rafael.pedido.mongodb.domain.State;

@SpringBootTest
public class PedidoRepositoryTest {

	@Autowired
	PedidoRepository pedidoRepository;
	
    @DisplayName("Test Save customer")
    @Test
    public void createPedidoTest() {
		Pedido pedidoNew = pedidoRepository.save(buildPedido());
    	Assertions.assertAll("pedido entity", 
    			() -> Assertions.assertEquals(new Long(1), pedidoNew.getId()),
    			() -> Assertions.assertEquals(new Long(1), pedidoNew.getCustomerId()),
    			() -> Assertions.assertEquals(new Double(0.00), pedidoNew.getTotal()),
    			() -> Assertions.assertEquals(3, pedidoNew.getItems().size()),
    			() -> Assertions.assertEquals(State.Pendiente, pedidoNew.getState()));

    	
    }
    
    @DisplayName("Test Update customer")
    @Test
    public void updatePedidoTest() {
    	Pedido pedido = buildPedido();
    	pedido.setTotal(250.00);
		Pedido pedidoNew = pedidoRepository.save(pedido);
    	Assertions.assertAll("pedido entity", 
    			() -> Assertions.assertEquals(new Long(1), pedidoNew.getId()),
    			() -> Assertions.assertEquals(new Long(1), pedidoNew.getCustomerId()),
    			() -> Assertions.assertEquals(new Double(250.00), pedidoNew.getTotal()),
    			() -> Assertions.assertEquals(3, pedidoNew.getItems().size()),
    			() -> Assertions.assertEquals(State.Pendiente, pedidoNew.getState()));
    }
    
    @DisplayName("Test delete pedido by primary key")
    @ParameterizedTest
    @ValueSource(longs = {1L})
    public void deleteCustomerById(Long id) {
    	pedidoRepository.deleteById(id);
    }

    @DisplayName("Test query by state")
    @ParameterizedTest
    @ValueSource(strings = {"Pendiente", "Aprovado", "Cancelado"})
    public void findByStateTest(String estado) {
    	List<Pedido> customers = pedidoRepository.findByState(State.getStateEnun(estado));
    	if (estado.equals("Pendiente")) {
        	Assertions.assertEquals(1, customers.size());
    	} else if (estado.equals("Aprovado")) {
        	Assertions.assertEquals(0, customers.size());
    	} else if (estado.equals("Cancelado")) {
        	Assertions.assertEquals(0, customers.size());
    	}
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
    			.state(State.Pendiente)
    			.items(items)
    			.build();
    	return pedido;		
    }
}
