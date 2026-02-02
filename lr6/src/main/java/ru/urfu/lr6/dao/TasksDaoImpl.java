package ru.urfu.lr6.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.urfu.lr6.entity.Tasks;

import java.util.List;

@Slf4j
@Repository
public class TasksDaoImpl implements TasksDao {

    @Autowired
    private EntityManager entityManager;

    @Override
    public List<Tasks> getAll() {
        var query = entityManager.createQuery("from Tasks");
        List<Tasks> tasks = query.getResultList();
        log.info("getAllTasks{}", tasks);
        return  tasks;
    }

    @Override
    public Tasks getOne(int id) {
        return entityManager.find(Tasks.class, id);
    }

    @Override
    public Tasks save(Tasks tasks) {
        return entityManager.merge(tasks);
    }

    @Override
    public void delete(int id) {
        Query query = entityManager.createQuery("delete from Tasks where id = :id");
        query.setParameter("id", id);
        query.executeUpdate();
    }
}
