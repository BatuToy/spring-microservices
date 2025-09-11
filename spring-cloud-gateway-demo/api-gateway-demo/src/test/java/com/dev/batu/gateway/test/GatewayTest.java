package com.dev.batu.gateway.test;

import com.dev.batu.gateway.dto.DummyDto;
import com.dev.batu.gateway.dto.Todo;
import com.dev.batu.gateway.rest.client.Client;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.UUID;

/**
 * @author batu
 */

import static org.junit.jupiter.api.Assertions.assertNotNull;


@Slf4j
@SpringBootTest
class GatewayTest {

    @Autowired
    private  Client client;

    @Test
    void restCallToTodosApi(){
        List<Todo> todos =  client.reqTodos();
        List<Long> userIds = todos.stream()
                .map(Todo::getUserId).toList();
        log.info("Todo userIds= {}", userIds);
        assertNotNull(userIds);
    }

    @Test
    void restCallToDummyApi(){
        List<DummyDto> dummies = client.reqDummies();
        List<UUID> dummyIds = dummies.stream().map(DummyDto::getId).toList();
        log.info("Dum ids= {}", dummyIds);
        assertNotNull(dummies);
    }
}
