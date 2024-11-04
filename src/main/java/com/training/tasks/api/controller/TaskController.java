package com.training.tasks.api.controller;

import com.training.tasks.api.model.Task;
import com.training.tasks.api.service.TaskService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/tasks")
public class TaskController {

    private final TaskService _taskService;

    public TaskController(TaskService taskService) {
        this._taskService = taskService;
    }

    @GetMapping
    public List<Task> getAllTasks() {
        return _taskService.getAllTasks();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Task> getTaskById(@PathVariable Long id) {
        return _taskService.getTaskById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Task createTask(@RequestBody Task task) {
        return _taskService.createTask(task);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Task> updateTask(@PathVariable Long id, @RequestBody Task taskDetails) {
        Optional<Task> updatedTask = _taskService.updateTask(id, taskDetails);
        return updatedTask.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTask(@PathVariable Long id) {
        _taskService.deleteTaskById(id);
        return ResponseEntity.noContent().build();
    }
}
