package com.neurowvu.rehabilitationapp.mapper;

import com.neurowvu.rehabilitationapp.dto.TaskDTO;
import com.neurowvu.rehabilitationapp.entity.Task;
import org.springframework.stereotype.Component;

@Component
public class TaskMapper {

    public TaskDTO mapTaskToDTO(Task task) {
        TaskDTO taskDTO = new TaskDTO();
        taskDTO.setId(task.getId());
        taskDTO.setDescription(task.getTaskDescription());

        return taskDTO;
    }

    public Task dtoToTask(TaskDTO taskDTO) {
        return new Task()
                .setTaskDescription(taskDTO.getDescription());
    }
}
