package com.example.speed.service;

import com.example.speed.entity.LogEntity;
import com.example.speed.repository.LogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class DefaultConfigService {
    @Autowired
    LogRepository logRepository;

    @EventListener(ApplicationReadyEvent.class)
    private void test(ApplicationReadyEvent event) {
        LogEntity logEntity = logRepository.findById(1l).orElse( new LogEntity());


    }
}
