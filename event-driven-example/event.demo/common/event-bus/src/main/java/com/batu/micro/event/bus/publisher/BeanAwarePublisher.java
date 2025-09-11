package com.batu.micro.event.bus.publisher;

/*
 * @created 28/07/2025 ~~ 12:14
 * author: batu
 */

import com.batu.micro.event.bus.handler.*;
import com.batu.micro.event.bus.registry.UseCaseRegistry;
import org.springframework.stereotype.Component;

@Component
public class BeanAwarePublisher {

    @SuppressWarnings("unchecked")
    public <T extends UseCase> void publish(T useCase) {
        VoidUseCaseHandler<T> handler = (VoidUseCaseHandler<T>) UseCaseRegistry.INSTANCE.fetchVoidUseCase(useCase.getClass());
        //validateHandler(handler);
        handler.handle(useCase);
    }

    @SuppressWarnings("unchecked")
    public <R, T extends UseCase> R publish(R type, T useCase) {
        UseCaseHandler<R, T> handler = (UseCaseHandler<R, T>) UseCaseRegistry.INSTANCE.fetchUseCase(useCase.getClass());
        //validateHandler(handler);
        return handler.handle(useCase);
    }

    @SuppressWarnings("unchecked")
    public <T> void publish(T data) {
        VoidNoUseCaseHandler<T> handler = (VoidNoUseCaseHandler<T>) UseCaseRegistry.INSTANCE.fetchVoidNoUseCaseHandler(data.getClass());
        //validateHandler();
        handler.handle(data);
    }

    @SuppressWarnings("unchecked")
    public <R, T> R publish(R type, T data) {
        NoUseCaseHandler<R, T> handler = (NoUseCaseHandler<R, T>) UseCaseRegistry.INSTANCE.fetchNoUseCaseHandler(data.getClass());
        //validateHandler();
        return handler.handle(data);
    }

    // todo -> validations will be come here !
}
