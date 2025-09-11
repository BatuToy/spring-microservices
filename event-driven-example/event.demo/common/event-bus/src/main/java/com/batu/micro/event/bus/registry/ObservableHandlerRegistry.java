package com.batu.micro.event.bus.registry;

import com.batu.micro.event.bus.handler.*;

/*
 * @created 28/07/2025 ~~ 12:27
 * author: batu
 */
public abstract class ObservableHandlerRegistry {

    public <R, T extends UseCase> void register(UseCaseHandler<R, T> handler, Class<T> useCaseClass) {
        UseCaseRegistry.INSTANCE.register(useCaseClass, handler);
    }

    public <T extends UseCase> void register(VoidUseCaseHandler<T> handler, Class<T> useCaseClass) {
        UseCaseRegistry.INSTANCE.register(useCaseClass, handler);
    }

    public <R, T> void register(NoUseCaseHandler<R, T> handler, Class<T> dataClass) {
        UseCaseRegistry.INSTANCE.register(dataClass, handler);
    }

    public <T> void register(VoidNoUseCaseHandler<T> handler, Class<T> dataClass) {
        UseCaseRegistry.INSTANCE.register(dataClass, handler);
    }

}
