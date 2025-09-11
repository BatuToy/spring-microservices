package com.batu.micro.common.domain.valueobject;

/*
 * @created 28/07/2025 ~~ 12:39
 * author: batu
 */
public abstract class BaseId<T> {

    private final T value;

    protected BaseId(T value) {
        this.value = value;
    }

    public T getValue() {
        return value;
    }
}
