package com.b_toy.outbox_pattern.controller;

import com.b_toy.outbox_pattern.dto.OrderRequestDto;
import com.b_toy.outbox_pattern.dto.OrderResponseDto;
import com.b_toy.outbox_pattern.services.OrderService;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/orders")
public class OrderController {

    private final OrderService orderService;

    @PostMapping
    public ResponseEntity<OrderResponseDto> createOrder(@RequestBody OrderRequestDto orderRequestDto){
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(
                        orderService.createOrder(orderRequestDto)
                );
    }
}
