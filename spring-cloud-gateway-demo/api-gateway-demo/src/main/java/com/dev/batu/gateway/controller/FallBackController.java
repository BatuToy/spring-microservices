package com.dev.batu.gateway.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import java.util.UUID;

@Slf4j
@RestController
public class FallBackController {


    @GetMapping(value = "/fallback")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public Mono<String> fallBack() {
        return Mono.just(UUID.randomUUID().toString());
    }

}
