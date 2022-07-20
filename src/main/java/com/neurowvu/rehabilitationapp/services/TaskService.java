package com.neurowvu.rehabilitationapp.services;

import com.neurowvu.rehabilitationapp.dto.TaskDTO;
import com.neurowvu.rehabilitationapp.entity.Task;
import com.neurowvu.rehabilitationapp.mapper.TaskMapper;
import com.neurowvu.rehabilitationapp.repositories.TasksRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskService {
    private final TasksRepository tasksRepository;
    private final TaskMapper taskMapper;
    @Autowired
    public TaskService(TasksRepository tasksRepository, TaskMapper taskMapper) {
        this.tasksRepository = tasksRepository;
        this.taskMapper = taskMapper;
    }

    public List<Task> getTasksList() {
        return tasksRepository.findAll();
    }

    public void saveTask(TaskDTO taskDTO) {
        Task task = taskMapper.dtoToTask(taskDTO);
        tasksRepository.save(task);
    }
}
