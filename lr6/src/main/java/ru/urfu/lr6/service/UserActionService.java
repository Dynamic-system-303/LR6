package ru.urfu.lr6.service;

import java.util.List;

public interface UserActionService {

    void log(String description);

    List<String> getAllLogs();
}
