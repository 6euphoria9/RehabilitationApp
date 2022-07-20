package com.neurowvu.rehabilitationapp.dto;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Data
public class TaskDTO {

    private Long id;
    private String description;

}
