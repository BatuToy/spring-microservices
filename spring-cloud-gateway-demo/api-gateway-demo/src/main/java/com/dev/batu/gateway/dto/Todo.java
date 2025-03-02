package com.dev.batu.gateway.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class Todo {
    private final Long userId;
    private final Long id;
    private final String title;
    private final Boolean completed;
}
