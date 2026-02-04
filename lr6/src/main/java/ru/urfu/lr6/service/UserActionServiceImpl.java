package ru.urfu.lr6.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
public class UserActionServiceImpl implements UserActionService{

    private final List<String> logs = new ArrayList<>();

    @Override
    public void log(String description) {
        String logEntry = LocalDateTime.now() + " - " + description;
        logs.add(logEntry);
        log.info(logEntry); // вывод в консоль и файл
    }

    @Override
    public List<String> getAllLogs() {
        return logs;
    }


}
