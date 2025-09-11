package com.batu.micro.event.bus.handler;

/*
 * @created 27/07/2025 ~~ 19:18
 * author: batu
 */
public interface VoidUseCaseHandler<T extends UseCase> {
    void handle(T useCase);
}
