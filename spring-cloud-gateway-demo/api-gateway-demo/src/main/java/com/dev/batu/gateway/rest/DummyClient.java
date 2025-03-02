package com.dev.batu.gateway.rest;

import com.dev.batu.gateway.dto.DummtDto;
import org.springframework.web.service.annotation.GetExchange;

import java.util.List;

public interface DummyClient {
    @GetExchange
    List<DummtDto> getDummies();
}
