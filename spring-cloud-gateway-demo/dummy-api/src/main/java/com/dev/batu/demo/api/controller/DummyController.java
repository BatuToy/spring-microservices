package com.dev.batu.demo.api.controller;

import com.dev.batu.demo.api.dto.DummyDto;
import com.dev.batu.demo.api.service.DummyService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/get")
public class DummyController {

    private final DummyService dummyService;

    @GetMapping
    public ResponseEntity<List<DummyDto>> getDummy(){
        List<DummyDto> dummyDtos = dummyService.getDummyData();
        log.info("dummies from Dummy-Service= {} ", dummyDtos.toString());
        return new ResponseEntity<>(
                dummyDtos,
                HttpStatus.OK
        );
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<DummyDto> getDummyById(@PathVariable("id") Integer id){
        return new ResponseEntity<>(
                dummyService.getDummyData().get(--id),
                HttpStatus.OK
        );
    }
}
