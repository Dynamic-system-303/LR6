package ru.urfu.lr6.service;

import org.springframework.stereotype.Service;
import ru.urfu.lr6.entity.TaskProgress;

import java.util.List;

@Service
public interface ProgressService {

    List<TaskProgress> getAll();
    TaskProgress getOne(int id);
    TaskProgress save(TaskProgress taskProgress);
    void delete(int id);
}
