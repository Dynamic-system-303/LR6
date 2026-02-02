package ru.urfu.lr6.service;

import org.springframework.boot.autoconfigure.kafka.KafkaProperties;
import org.springframework.transaction.annotation.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.urfu.lr6.dao.TasksDao;
import ru.urfu.lr6.entity.Role;
import ru.urfu.lr6.entity.Tasks;
import ru.urfu.lr6.entity.User;


import java.util.List;
@Slf4j
@Service
public class TaskServiceServiceImpl implements TaskService {

    @Autowired
    private TasksDao tasksDao;

    @Override
    @Transactional
    public List<Tasks> getAll() {
        List<Tasks> tasks = tasksDao.getAll();
        log.info("Fetched all tasks: {}", tasks);
        return tasks;
    }

    @Override
    @Transactional
    public Tasks getOne(int id) {
        Tasks task = tasksDao.getOne(id);
        log.info("Fetched task by id {}: {}", id, task);
        return task;
    }

    @Override
    @Transactional
    public Tasks save(Tasks task, boolean isAdmin) {
        if (!isAdmin) {
            Tasks existingTask = tasksDao.getOne(task.getId());
            existingTask.setStatus(task.getStatus());
            Tasks updated = tasksDao.save(existingTask);
            log.info("Updated task status by USER: {}", updated);
            return updated;
        }
        Tasks savedTask = tasksDao.save(task);
        log.info("Saved/Updated task by ADMIN: {}", savedTask);
        return savedTask;
    }

    @Override
    @Transactional
    public void delete(int id, boolean isAdmin) {
        if (!isAdmin) {
            log.warn("User tried to delete task without ADMIN rights: {}", id);
            throw new RuntimeException("Only admin can delete tasks");
        }
        tasksDao.delete(id);
        log.info("Deleted task with id {}", id);
    }
}
