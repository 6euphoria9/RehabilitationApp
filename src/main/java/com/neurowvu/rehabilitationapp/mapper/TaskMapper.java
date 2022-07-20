package com.neurowvu.rehabilitationapp.mapper;

import com.neurowvu.rehabilitationapp.dto.TaskDTO;
import com.neurowvu.rehabilitationapp.entity.Task;
import org.springframework.stereotype.Component;

@Component
public class TaskMapper {

    public Task dtoToTask(TaskDTO taskDTO) {
        return new Task()
                .setTaskDescription(taskDTO.getDescription());
    }


}
