package com.b_toy.outbox_pattern.config.beans;

import com.b_toy.outbox_pattern.config.mapper.Mapper;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

@Configuration
public class BeansConfig {

    @Bean
    public Mapper modelMapper(){
        return new Mapper(new ModelMapper());
    }
}
