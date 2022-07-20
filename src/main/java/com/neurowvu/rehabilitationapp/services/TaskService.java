package com.neurowvu.rehabilitationapp.services;

import com.neurowvu.rehabilitationapp.entity.Task;
import com.neurowvu.rehabilitationapp.repositories.TasksRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskService {
    private final TasksRepository tasksRepository;
    @Autowired
    public TaskService(TasksRepository tasksRepository) {
        this.tasksRepository = tasksRepository;
    }

    public List<Task> getTasksList() {
        return tasksRepository.findAll();
    }
}
