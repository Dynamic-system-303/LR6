package ru.urfu.lr6.dao;

import org.springframework.stereotype.Repository;
import ru.urfu.lr6.entity.TaskProgress;

import java.util.List;

@Repository
public interface TaskProgressDao {
    public List<TaskProgress> getAllProgress();
    public TaskProgress getOneProgress(int id);
    public TaskProgress saveProgress(TaskProgress taskProgress);
    public void deleteProgress(int id);
}
