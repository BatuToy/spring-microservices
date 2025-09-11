package com.batu.micro.event.bus.handler;

/*
 * @created 27/07/2025 ~~ 19:21
 * author: batu
 */
public interface VoidNoUseCaseHandler<T> {
    void handle(T data);
}
