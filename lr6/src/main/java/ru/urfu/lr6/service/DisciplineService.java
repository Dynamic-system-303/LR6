package ru.urfu.lr6.service;

import org.springframework.stereotype.Service;
import ru.urfu.lr6.entity.Discipline;
import ru.urfu.lr6.entity.Student;

import java.util.List;

@Service
public interface DisciplineService {
    List<Discipline> getAll();
    Discipline getOne(int id);
    Discipline save(Discipline discipline);
    void delete(int id);
}
