package com.example.speed.service;

import com.example.speed.entity.TerminalEntity;
import com.example.speed.repository.TerminalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

@Service
@EnableScheduling
public class ScheduleService {
    @Autowired
    TerminalRepository terminalRepository;

    @Value("${app.constant.split-index}")
    private String splitIndex;

    @Value("${app.constant.pc-num}")
    private long pcNum;

    @Scheduled(cron = "0 * * * * *")
    private void executeTerminal() {
        TerminalEntity terminalEntity = terminalRepository.findById(pcNum).orElse(new TerminalEntity());

        if (terminalEntity.getStatus()) {
            String[] args = terminalEntity.getCommand().split(splitIndex);
            try {
                ProcessBuilder builder = new ProcessBuilder(args);
                builder.redirectErrorStream(true);
                Process process = builder.start();
                BufferedReader r = new BufferedReader(new InputStreamReader(process.getInputStream()));
                String output = "";
                String line;
                while (true) {
                    line = r.readLine();
                    if (line == null) { break; }
                    System.out.println(line);
                    output = output + "\n" + line;
                }
                terminalEntity.setOutput(output);
                terminalRepository.save(terminalEntity);

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Scheduled(cron = "*/10 * * * * *")
    private void executeTerminal2() {
        TerminalEntity terminalEntity = terminalRepository.findById(pcNum - 1).orElse(new TerminalEntity());

        if (terminalEntity.getStatus()) {
            String[] args = terminalEntity.getCommand().split(splitIndex);
            try {
                ProcessBuilder builder = new ProcessBuilder(args);
                builder.redirectErrorStream(true);
                Process process = builder.start();
                BufferedReader r = new BufferedReader(new InputStreamReader(process.getInputStream()));
                String output = "";
                String line;
                while (true) {
                    line = r.readLine();
                    if (line == null) { break; }
                    System.out.println(line);
                    output = output + "\n" + line;
                }
                terminalEntity.setOutput(output);
                terminalRepository.save(terminalEntity);

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}
