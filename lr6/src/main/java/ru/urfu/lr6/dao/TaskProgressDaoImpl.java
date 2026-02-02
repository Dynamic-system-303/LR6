package ru.urfu.lr6.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.urfu.lr6.entity.TaskProgress;

import java.util.List;

@Slf4j
@Repository
public class TaskProgressDaoImpl implements TaskProgressDao {

    @Autowired
    private EntityManager entityManager;

    @Override
    public List<TaskProgress> getAllProgress() {
        Query query = entityManager.createQuery("from TaskProgress");
        List<TaskProgress> taskProgresses = query.getResultList();
        log.info("getAllProgress{}", taskProgresses);
        return taskProgresses;
    }

    @Override
    public TaskProgress getOneProgress(int id) {
        return entityManager.find(TaskProgress.class, id);
    }

    @Override
    public TaskProgress saveProgress(TaskProgress taskProgress) {
        return entityManager.merge(taskProgress);
    }

    @Override
    public void deleteProgress(int id) {
        Query query = entityManager.createQuery("delete from TaskProgress where id = :id");
        query.setParameter("id", id);
        query.executeUpdate();
    }

}
