package ru.urfu.lr6.service;

import org.springframework.stereotype.Service;
import ru.urfu.lr6.entity.Tasks;
import ru.urfu.lr6.entity.User;

import java.util.List;


public interface TaskService {
    List<Tasks> getTasksForUser(User user);
    Tasks getTask(int id, User user);
    Tasks createTask(Tasks task, User user);
    Tasks updateTask(Tasks task, User user);
    void deleteTask(int id, User user);

//    List<Tasks> getAllTasks();
//
//    List<Tasks> getTasksForUserByUsername(String username);
//
//    Tasks createTaskForUsername(Tasks task, String username);
//
//    void deleteTaskByUsername(int id, String username);
}
