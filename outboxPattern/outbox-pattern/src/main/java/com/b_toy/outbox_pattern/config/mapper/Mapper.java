package com.b_toy.outbox_pattern.config.mapper;

import com.b_toy.outbox_pattern.model.Order;
import com.b_toy.outbox_pattern.model.Outbox;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Configuration;

public class Mapper {

    private final ModelMapper modelMapper;

    public Mapper(ModelMapper modelMapper){
        this.modelMapper = modelMapper;
        configs();
    }
    // This will see all the types of it.
    public <S,T> T map(S source,Class<T> target){
        return modelMapper.map(source, target);
    }

    public void configs(){
        assert modelMapper != null;
        modelMapper.typeMap(Order.class, Outbox.class).addMappings(mapper -> {
            mapper.map(Order::toString, Outbox::setPayload);
            mapper.map(src -> src.getId().toString(), Outbox::setAggregateId);
        });
    }
}
