package ru.urfu.lr6.service;

import org.springframework.stereotype.Service;
import ru.urfu.lr6.entity.Tasks;

import java.util.List;

@Service
public interface TaskService {
    List<Tasks> getAll();
    Tasks getOne(int id);
    Tasks save(Tasks task, boolean isAdmin);
    void delete(int id, boolean isAdmin);
}
