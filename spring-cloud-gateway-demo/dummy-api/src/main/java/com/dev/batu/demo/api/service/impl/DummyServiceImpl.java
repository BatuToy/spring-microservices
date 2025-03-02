package com.dev.batu.demo.api.service.impl;

import com.dev.batu.demo.api.dto.DummyDto;
import com.dev.batu.demo.api.model.DummyEntity;
import com.dev.batu.demo.api.service.DummyService;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class DummyServiceImpl implements DummyService {


    @Override
    public List<DummyDto> getDummyData() {
        List<DummyEntity> payload = new ArrayList<>();
        payload.add(new DummyEntity(UUID.randomUUID(), "f1", "l1", 10));
        payload.add(new DummyEntity(UUID.randomUUID(), "f2", "l2", 20));
        payload.add(new DummyEntity(UUID.randomUUID(), "f3", "l3", 30));
        return payload.stream()
                .map(dummyEntity -> DummyDto.builder()
                        .id(dummyEntity.getId())
                        .firstName(dummyEntity.getFirstName())
                        .build())
                .toList();
    }
}
