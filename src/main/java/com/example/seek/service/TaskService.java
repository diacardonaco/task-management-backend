package com.example.seek.service;

import com.example.seek.dto.TaskDTO;
import com.example.seek.entity.Task;
import com.example.seek.exception.TaskNotFoundException;
import com.example.seek.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class TaskService {

    @Autowired
    private TaskRepository taskRepository;

    public List<Task> getAllTasks() {
        return taskRepository.findAll();
    }

    public Task createTask(TaskDTO taskDTO) {
        Task task = Task.builder()
                .title(taskDTO.getTitle())
                .description(taskDTO.getDescription())
                .status(taskDTO.getStatus())
                .build();
        return taskRepository.save(task);
    }
    public Task updateTask(Long id, TaskDTO updatedTaskDTO) {
        return taskRepository.findById(id)
                .map(task -> {
                    task.setTitle(updatedTaskDTO.getTitle());
                    task.setDescription(updatedTaskDTO.getDescription());
                    task.setStatus(updatedTaskDTO.getStatus());
                    return taskRepository.save(task);
                }).orElseThrow(() -> new TaskNotFoundException("Task not found"));
    }

    public void deleteTask(Long id) {
        if (!taskRepository.existsById(id)) {
            throw new TaskNotFoundException("Task not found");
        }
        taskRepository.deleteById(id);
    }
}