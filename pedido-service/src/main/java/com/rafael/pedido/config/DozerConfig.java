package com.rafael.pedido.config;

import java.util.ArrayList;
import java.util.List;

import org.dozer.CustomConverter;
import org.dozer.DozerBeanMapper;
import org.dozer.loader.api.BeanMappingBuilder;
import org.dozer.loader.api.FieldsMappingOptions;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.rafael.pedido.dtos.ItemDto;
import com.rafael.pedido.dtos.PedidoDto;
import com.rafael.pedido.dtos.converters.StateToStringConverter;
import com.rafael.pedido.dtos.converters.StringToStateConverter;
import com.rafael.pedido.mongodb.domain.Item;
import com.rafael.pedido.mongodb.domain.Pedido;
import com.rafael.pedido.mongodb.domain.State;

@Configuration
public class DozerConfig {
	
	@Bean(name = "org.dozer.Mapper")
    public DozerBeanMapper mapper() throws Exception {
      DozerBeanMapper mapper = new DozerBeanMapper();
      mapper.addMapping(objectMappingBuilder);
      mapper.setCustomConverters(getCustomConverters());
      return mapper;
    }
    
    BeanMappingBuilder objectMappingBuilder = new BeanMappingBuilder() {
        @Override
        protected void configure() {
        	mapping(String.class, State.class );
        	mapping(Item.class, ItemDto.class);
            mapping(Pedido.class, PedidoDto.class)
                .fields("state", "state", FieldsMappingOptions.customConverter(StateToStringConverter.class) );
        }
    };
    
    private List<CustomConverter> getCustomConverters() {
    	List<CustomConverter> converters = new ArrayList<>();
    	StateToStringConverter stateToStringConverter = new StateToStringConverter();
    	StringToStateConverter stringToStateConverter = new StringToStateConverter();
    	converters.add(stateToStringConverter);
    	converters.add(stringToStateConverter);
    	return converters;
    }
}
