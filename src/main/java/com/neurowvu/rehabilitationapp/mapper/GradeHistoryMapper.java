package com.neurowvu.rehabilitationapp.mapper;

import com.neurowvu.rehabilitationapp.dto.GradeHistoryDTO;
import com.neurowvu.rehabilitationapp.entity.Feedback;
import com.neurowvu.rehabilitationapp.entity.Grade;
import com.neurowvu.rehabilitationapp.entity.Prescription;
import org.springframework.stereotype.Component;

@Component
public class GradeHistoryMapper {

    public GradeHistoryDTO mapToGradeHistoryDTO(Feedback feedback, Grade grade) {
        return new GradeHistoryDTO()
                .setDate(feedback.getDate())
                .setName(feedback.getPrescription().getTask().getTaskDescription())
                .setFeedBackId(feedback.getId())
                .setGrade(grade.getGrade());
    }

}
