package com.dev.batu.gateway.rest;

import com.dev.batu.gateway.dto.DummyDto;
import org.springframework.web.service.annotation.GetExchange;

import java.util.List;

public interface DummyClient {
    @GetExchange
    List<DummyDto> getDummies();
}
