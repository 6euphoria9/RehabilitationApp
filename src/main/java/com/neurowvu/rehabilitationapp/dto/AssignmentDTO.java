package com.neurowvu.rehabilitationapp.dto;

import lombok.Data;

@Data
public class AssignmentDTO {

    private Long id;
    private String comment;
    private Long patientId;
    private Long taskId;
    private String taskName;
    private Long weekly;
    private Long daily;
    private Long sets;
    private Long reps;
    private Long duration;


    public AssignmentDTO() {
    }
}
