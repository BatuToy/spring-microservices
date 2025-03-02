package com.dev.batu.demo.api.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.util.UUID;

@AllArgsConstructor
@Getter
@Builder
public class DummyDto {
    private final UUID id;
    private final String firstName;
}
