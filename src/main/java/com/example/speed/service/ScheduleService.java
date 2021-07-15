package com.example.speed.service;

import com.example.speed.entity.TerminalEntity;
import com.example.speed.repository.TerminalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
@EnableScheduling
public class ScheduleService {
    @Autowired
    TerminalRepository terminalRepository;

    @Value("${app.constant.split-index}")

    @Scheduled(cron = "* * * * * *")
    private void executeTerminal() {
        TerminalEntity terminalEntity = terminalRepository.findById(5l).orElse(new TerminalEntity());

        if (terminalEntity.getStatus()) {
            String[] args = terminalEntity.getCommand().split(";7421;");
            try {
                Process proc = new ProcessBuilder(args).start();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}
