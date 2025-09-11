package com.batu.micro.event.bus.registry;

import com.batu.micro.event.bus.handler.*;

import java.util.HashMap;
import java.util.logging.Logger;

/*
 * @created 27/07/2025 ~~ 19:21
 * author: batu
 */

public class UseCaseRegistry {

    private static final Logger log = Logger.getLogger(UseCaseRegistry.INSTANCE.getClass().getName());

    private HashMap<Class<? extends UseCase> , UseCaseHandler<?, ? extends UseCase>> useCaseHandlerRegistry;
    private HashMap<Class<? extends UseCase>, VoidUseCaseHandler<? extends UseCase>> voidUseCaseHandlerRegistry;
    private HashMap<Class<?>, NoUseCaseHandler<?, ?>> noUseCaseHandlerRegistry;
    private HashMap<Class<?> , VoidNoUseCaseHandler<?>> voidNoUseCaseHandlerRegistry;

    public static final UseCaseRegistry INSTANCE = new UseCaseRegistry();

    private UseCaseRegistry() {
        this.useCaseHandlerRegistry = new HashMap<>();
        this.noUseCaseHandlerRegistry = new HashMap<>();
        this.voidNoUseCaseHandlerRegistry = new HashMap<>();
        this.voidUseCaseHandlerRegistry = new HashMap<>();
    }

    public <R, T extends UseCase> void register(Class<T> useCase, UseCaseHandler<R, T> handler) {
        useCaseHandlerRegistry.put(useCase, handler);
        log.info("UseCase data with name "+ useCase.getSimpleName() + " registered the use case handler with name " +
                handler.getClass().getSimpleName() + " successfully !");
    }

    public <T extends UseCase> void register(Class<T> useCase, VoidUseCaseHandler<T> handler) {
        voidUseCaseHandlerRegistry.put(useCase, handler);
        log.info("Void use case data with name "+ useCase.getSimpleName() + " registered the void use case handler with name " +
                handler.getClass().getSimpleName() + " successfully !");
    }

    public <R, T> void register(Class<T> noUseCase, NoUseCaseHandler<R, T> handler) {
        noUseCaseHandlerRegistry.put(noUseCase, handler);
        log.info("No use case data with name "+ noUseCase.getSimpleName() + " registered the no use case handler with name " +
                handler.getClass().getSimpleName() + " successfully !");
    }

    public <T> void register(Class<T> voidNoUseCase, VoidNoUseCaseHandler<T> handler) {
        voidNoUseCaseHandlerRegistry.put(voidNoUseCase, handler);
        log.info("Void no use case data with name "+ voidNoUseCase.getSimpleName() + " registered the void no use case handler with name " +
                handler.getClass().getSimpleName() + " successfully !");
    }

    @SuppressWarnings("unchecked")
    public <R, T extends UseCase> UseCaseHandler<R, T> fetchUseCase(Class<T> useCase) {
        return (UseCaseHandler<R, T>) useCaseHandlerRegistry.get(useCase);
    }

    @SuppressWarnings("unchecked")
    public <T extends UseCase> VoidUseCaseHandler<T> fetchVoidUseCase(Class<T> useCase) {
        return (VoidUseCaseHandler<T>) useCaseHandlerRegistry.get(useCase);
    }

    @SuppressWarnings("unchecked")
    public <R, T> NoUseCaseHandler<R, T> fetchNoUseCaseHandler(Class<T> useCase) {
        return (NoUseCaseHandler<R, T>) noUseCaseHandlerRegistry.get(useCase);
    }

    @SuppressWarnings("unchecked")
    public <T> VoidNoUseCaseHandler<T> fetchVoidNoUseCaseHandler(Class<T> data) {
        return (VoidNoUseCaseHandler<T>) voidNoUseCaseHandlerRegistry.get(data);
    }
}
