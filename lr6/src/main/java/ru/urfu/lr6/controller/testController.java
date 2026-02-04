//package ru.urfu.lr6.controller;
//
//import lombok.RequiredArgsConstructor;
//import org.springframework.scheduling.config.Task;
//import org.springframework.web.bind.annotation.*;
//import ru.urfu.lr6.entity.Tasks;
//import ru.urfu.lr6.service.TaskService;
//
//import java.util.List;
//
//@RestController
//@RequestMapping("/api/tasks")
//@RequiredArgsConstructor
//
//public class testController {
//
//    private final TaskService tasksService;
//
////    public testController (TaskService taskService) {
////        this.tasksService = taskService;
////    }
//
//    // Получить все задачи (ADMIN)
//    @GetMapping("/all")
//    public List<Tasks> getAllTasks() {
//        return tasksService.getAllTasks(); // метод должен вернуть все задачи
//    }
//
//    // Получить задачи конкретного пользователя (USER)
//    @GetMapping("/mine")
//    public List<Tasks> getMyTasks(@RequestParam String username) {
//        return tasksService.getTasksForUserByUsername(username);
//    }
//
//    // Создать задачу
//    @PostMapping
//    public Tasks createTask(@RequestBody Tasks task,
//                            @RequestParam String username) {
//        return tasksService.createTaskForUsername(task, username);
//    }
//
//    // Удалить задачу
//    @DeleteMapping("/{id}")
//    public void deleteTask(@PathVariable int id,
//                           @RequestParam String username) {
//        tasksService.deleteTaskByUsername(id, username);
//    }
//}
