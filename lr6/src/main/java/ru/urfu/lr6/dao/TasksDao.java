package ru.urfu.lr6.dao;

import org.springframework.stereotype.Repository;
import ru.urfu.lr6.entity.Tasks;

import java.util.List;

@Repository
public interface TasksDao {
    public List<Tasks> getAll();
    public Tasks getOne(int id);
    public Tasks save(Tasks tasks);
    public void delete(int id);
}
