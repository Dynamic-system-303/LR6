package ru.urfu.lr6.dao;

import org.springframework.stereotype.Repository;
import ru.urfu.lr6.entity.Discipline;

import java.util.List;

@Repository
public interface DisciplineDao {
    public List<Discipline> getAll();
    public Discipline getOne(int id);
    public Discipline save(Discipline discipline);
    public void delete(int id);
}
