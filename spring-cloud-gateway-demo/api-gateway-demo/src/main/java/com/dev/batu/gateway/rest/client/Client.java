package com.dev.batu.gateway.rest.client;

import com.dev.batu.gateway.dto.DummtDto;
import com.dev.batu.gateway.dto.Todo;
import com.dev.batu.gateway.rest.DummyClient;
import com.dev.batu.gateway.rest.TodosClient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;


import java.util.List;

@RequiredArgsConstructor
@Component
public class Client {

    private final TodosClient todosClient;
    private final DummyClient dummyClient;

    // Think like a Rest Service bypassing all the req-res model in here.
    // Can make filters in here is probably best-practice.

    public List<Todo> reqTodos(){
        return todosClient.todos();
    }

    public List<DummtDto> reqDummies(){
        return dummyClient.getDummies();
    }
}
