package ru.urfu.lr6.service;

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
public class TaskServiceImpl implements TaskService {

    private final TasksDao tasksDao;

    @Autowired
    public TaskServiceImpl(TasksDao tasksDao) {
        this.tasksDao = tasksDao;
    }



//    private UserActionService userActionService;
//
//    public TaskServiceImpl(TasksDao taskDao,
//                           UserActionService userActionService) {
//        this.taskDao = taskDao;
//        this.userActionService = userActionService;
//    }

    @Override
    @Transactional
    public List<Tasks> getTasksForUser(User user) {

//        if (user.getRole() == Role.ADMIN) {
//            return tasksDao.getAll();
//        }
        if (user.getRole() == Role.USER) {
            return tasksDao.findByCreatedBy(user.getUsername());
        }
        else {
            // READ_ONLY
            return tasksDao.getAll();
        }
    }

    // Получить задачу
    @Override
    @Transactional(readOnly = true)
    public Tasks getTask(int id, User user) {
        Tasks task = tasksDao.getOne(id);
        if(task == null){
            throw new RuntimeException("Task not found");
        }
        // Проверка доступа
        if(user.getRole() == Role.USER && !task.getCreatedBy().equals(user.getUsername())){
            throw new RuntimeException("Access denied");
        }
        return task;
    }

    // Создание задачи
    @Override
    @Transactional
    public Tasks createTask(Tasks task, User user) {
        if(user.getRole() == Role.READ_ONLY){
            throw new RuntimeException("Access denied");
        }
        task.setCreatedBy(user.getUsername()); // Устанавливаем автора
        task.setStatus("NEW"); // Можно статус по умолчанию
        Tasks saved = tasksDao.save(task);
        log.info("Task created: {}", saved);
        return saved;
    }

    // Редактирование задачи
    @Override
    @Transactional
    public Tasks updateTask(Tasks task, User user) {
        Tasks existing = tasksDao.getOne(task.getId());
        if(existing == null){
            throw new RuntimeException("Task not found");
        }
        if(user.getRole() == Role.USER && !existing.getCreatedBy().equals(user.getUsername())){
            throw new RuntimeException("Access denied");
        }
        existing.setTitle(task.getTitle());
        existing.setDescription(task.getDescription());
        existing.setStartDate(task.getStartDate());
        existing.setEndDate(task.getEndDate());
        existing.setStatus(task.getStatus());
        Tasks updated = tasksDao.save(existing);
        log.info("Task updated: {}", updated);
        return updated;
    }

    // Удаление задачи
    @Override
    @Transactional
    public void deleteTask(int id, User user) {
        Tasks task = tasksDao.getOne(id);
        if(task == null){
            throw new RuntimeException("Task not found");
        }
        if(user.getRole() == Role.USER && !task.getCreatedBy().equals(user.getUsername())){
            throw new RuntimeException("Access denied");
        }
        tasksDao.delete(id);
        log.info("Task deleted: {}", id);
    }



//    @Override
//    public List<Tasks> getAllTasks() {
//        return tasksDao.getAll();
//    }
//
//    @Override
//    public List<Tasks> getTasksForUserByUsername(String username) {
//        return tasksDao.findByCreatedBy(username);
//    }
//
//    @Override
//    public Tasks createTaskForUsername(Tasks task, String username) {
//        task.setCreatedBy(username);
//        return tasksDao.save(task);
//    }
//
//    @Override
//    public void deleteTaskByUsername(int id, String username) {
//        Tasks task = tasksDao.getOne(id);
//        if (task.getCreatedBy().equals(username)) {
//            tasksDao.delete(id);
//        } else {
//            throw new RuntimeException("Access denied");
//        }
//    }
}
