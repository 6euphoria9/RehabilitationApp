package com.neurowvu.rehabilitationapp.dto;

import lombok.Data;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;

@Data
@Accessors(chain = true)
public class GradeHistoryDTO {

    private LocalDateTime date;
    private String name;
    private Short grade;

}
