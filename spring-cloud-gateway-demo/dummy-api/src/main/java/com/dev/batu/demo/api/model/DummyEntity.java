package com.dev.batu.demo.api.model;

import lombok.*;

import java.util.UUID;

@Getter
@AllArgsConstructor
public class DummyEntity {
    private final UUID id;
    private final String firstName;
    private final String lastName;
    private final Integer age;
}
