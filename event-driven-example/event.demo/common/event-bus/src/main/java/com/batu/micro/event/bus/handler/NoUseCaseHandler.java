package com.batu.micro.event.bus.handler;

/*
 * @created 27/07/2025 ~~ 19:20
 * author: batu
 */
public interface NoUseCaseHandler<R, T> {
    R handle(T data);
}
