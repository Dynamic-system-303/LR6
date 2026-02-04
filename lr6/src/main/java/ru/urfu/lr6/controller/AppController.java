package ru.urfu.lr6.controller;


import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.urfu.lr6.entity.Role;
import ru.urfu.lr6.entity.Tasks;
import ru.urfu.lr6.entity.User;
import ru.urfu.lr6.service.TaskService;
import ru.urfu.lr6.service.TaskServiceImpl;
import ru.urfu.lr6.service.UserActionService;
import ru.urfu.lr6.service.UserService;

import java.util.List;

@Slf4j
@Controller
@RequestMapping("/")
public class AppController {
    private final TaskService tasksService;
    private final UserService userService;
    private final UserActionService userActionService;

    @Autowired
    public AppController(TaskService tasksService,
                         UserService userService,
                         UserActionService userActionService) {
        this.tasksService = tasksService;
        this.userService = userService;
        this.userActionService = userActionService;
    }

    // Главная страница

    @GetMapping
    public String home() {
        return "index"; // возвращает templates/index.html
    }

    // О приложении

    @GetMapping("/about")
    public String about() {
        return "about"; // возвращает templates/about.html
    }

    // Список задач

    @GetMapping("/tasks")
    public String listTasks(Model model,
                            @SessionAttribute("user") User user) {

        // Получаем список задач в зависимости от роли пользователя
        List<Tasks> tasks = tasksService.getTasksForUser(user);
        model.addAttribute("tasks", tasks);
        model.addAttribute("role", user.getRole());

        return "tasks/list"; // templates/tasks/list.html
    }

    // Создание задачи - форма

    @GetMapping("/tasks/create")
    public String createTaskForm(Model model) {
        model.addAttribute("task", new Tasks());
        return "tasks/create"; // templates/tasks/create.html
    }

    // Создание задачи - сохранение

    @PostMapping("/tasks/create")
    public String createTask(@ModelAttribute("task") Tasks task,
                             @SessionAttribute("user") User user) {

        tasksService.createTask(task, user);
        userActionService.log("User " + user.getUsername() + " created task: " + task.getTitle());

        return "redirect:/tasks"; // возвращаемся к списку задач
    }


    // Редактирование задачи - форма

    @GetMapping("/tasks/edit/{id}")
    public String editTaskForm(@PathVariable("id") int id,
                               Model model,
                               @SessionAttribute("user") User user) {

        Tasks task = tasksService.getTask(id, user); // сервис проверяет права
        model.addAttribute("task", task);
        return "tasks/edit"; // templates/tasks/edit.html
    }


    // Редактирование задачи - сохранение

    @PostMapping("/tasks/edit")
    public String editTask(@ModelAttribute("task") Tasks task,
                           @SessionAttribute("user") User user) {

        tasksService.updateTask(task, user);
        userActionService.log("User " + user.getUsername() + " edited task: " + task.getTitle());

        return "redirect:/tasks";
    }

    // Удаление задачи

    @GetMapping("/tasks/delete/{id}")
    public String deleteTask(@PathVariable("id") int id,
                             @SessionAttribute("user") User user) {

        tasksService.deleteTask(id, user);
        userActionService.log("User " + user.getUsername() + " deleted task with ID: " + id);

        return "redirect:/tasks";
    }

    // Управление ролями (ADMIN)

    @GetMapping("/roles")
    public String roleForm(Model model, @SessionAttribute("user") User user) {
        if(user.getRole() != Role.ADMIN){
            return "redirect:/tasks"; // если не ADMIN, обратно к задачам
        }
        model.addAttribute("users", userService.getAllUser());
        return "roles"; // templates/roles.html
    }

    @PostMapping("/roles")
    public String changeRole(@RequestParam String username,
                             @RequestParam Role role,
                             @SessionAttribute("user") User currentUser) {
        if(currentUser.getRole() != Role.ADMIN){
            return "redirect:/tasks";
        }

        userService.changeRole(username, role);
        userActionService.log("User " + currentUser.getUsername() + " changed role of " + username + " to " + role);
        return "redirect:/roles";
    }


    // Просмотр логов (ADMIN)

    @GetMapping("/logs")
    public String viewLogs(Model model, @SessionAttribute("user") User user) {
        if(user.getRole() != Role.ADMIN){
            return "redirect:/tasks";
        }
        model.addAttribute("logs", userActionService.getAllLogs());
        return "logs"; // templates/logs.html
    }
}
