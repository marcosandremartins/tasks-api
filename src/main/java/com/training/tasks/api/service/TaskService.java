package com.training.tasks.api.service;

import com.training.tasks.api.model.Task;
import com.training.tasks.api.repository.TaskRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TaskService {

    private final TaskRepository _taskRepository;

    public TaskService(TaskRepository taskRepository) {
        this._taskRepository = taskRepository;
    }

    public List<Task> getAllTasks() {
        return _taskRepository.findAll();
    }

    public Optional<Task> getTaskById(Long id) {
        return _taskRepository.findById(id);
    }

    public Task createTask(Task task) {
        if (task.getId() == null) {
            Long maxId = _taskRepository.findMaxId();
            task.setId(maxId == null ? 1L : maxId + 1L);
        }

        return _taskRepository.save(task);
    }

    public Optional<Task> updateTask(Long id, Task taskDetails) {
        return _taskRepository.findById(id).map(existingTask -> {

            if (taskDetails.getTitle() != null) {
                existingTask.setTitle(taskDetails.getTitle());
            }
            if (taskDetails.getDescription() != null) {
                existingTask.setDescription(taskDetails.getDescription());
            }

            existingTask.setCompleted(taskDetails.isCompleted());

            return _taskRepository.save(existingTask);
        });
    }

    public void deleteTaskById(Long id) {
        Optional<Task> task = _taskRepository.findById(id);
        task.ifPresent(_taskRepository::delete);
    }
}
