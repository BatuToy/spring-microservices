package com.dev.batu.gateway.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@AllArgsConstructor
@Getter
@Setter
public class DummyDto {
    private final UUID id;
    private final String firstName;
}
