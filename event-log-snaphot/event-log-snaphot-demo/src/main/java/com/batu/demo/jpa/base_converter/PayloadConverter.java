package com.batu.demo.jpa.base_converter;

import com.batu.demo.mapper.ObjectMapperUtil;
import com.fasterxml.jackson.core.type.TypeReference;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class PayloadConverter<T> implements AttributeConverter<T, String> {

    private final TypeReference<T> ref;

    public PayloadConverter(TypeReference<T> ref) {
        this.ref = ref;
    }

    @Override
    public String convertToDatabaseColumn(T t) {
        return ObjectMapperUtil.toString(t);
    }

    @Override
    public T convertToEntityAttribute(String payloadString) {
        return ObjectMapperUtil.toObject(payloadString, ref);
    }
}
