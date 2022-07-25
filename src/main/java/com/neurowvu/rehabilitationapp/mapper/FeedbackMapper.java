package com.neurowvu.rehabilitationapp.mapper;

import com.neurowvu.rehabilitationapp.dto.FeedbackDTO;
import com.neurowvu.rehabilitationapp.entity.*;
import com.neurowvu.rehabilitationapp.services.PatientService;
import com.neurowvu.rehabilitationapp.services.PrescriptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class FeedbackMapper {
    private final PrescriptionService prescriptionService;
    private final PatientService patientService;

    @Autowired
    public FeedbackMapper(PrescriptionService prescriptionService, PatientService patientService) {
        this.prescriptionService = prescriptionService;
        this.patientService = patientService;
    }

    public Grade mapFeedbackToGrade(Feedback feedback) {
        return null;
    }

    public FeedbackDTO mapFeedbackToDTO(Feedback feedback) {
        FeedbackDTO feedbackDTO = new FeedbackDTO();
        feedbackDTO.setTaskName(feedback.getTask().getTaskDescription());

        Metric assigned = feedback.getMetric();
        Metric completed = feedback.getPrescription().getMetric();

        feedbackDTO.setCompletedWeekly(completed.getWeekly());
        feedbackDTO.setExpectedWeekly(assigned.getWeekly());

        feedbackDTO.setCompletedDaily(completed.getDaily());
        feedbackDTO.setExpectedDaily(assigned.getDaily());

        feedbackDTO.setCompletedSets(completed.getSets());
        feedbackDTO.setExpectedSets(assigned.getSets());

        feedbackDTO.setCompletedReps(completed.getReps());
        feedbackDTO.setExpectedReps(assigned.getReps());

        return feedbackDTO;
    }
}
