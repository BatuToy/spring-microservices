package com.dev.batu.gateway.rest;

import com.dev.batu.gateway.dto.Todo;
import org.springframework.web.service.annotation.GetExchange;

import java.util.List;

public interface TodosClient {
    @GetExchange
    List<Todo> todos();
}
