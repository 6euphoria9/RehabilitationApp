package com.neurowvu.rehabilitationapp.mapper;

import com.neurowvu.rehabilitationapp.entity.Feedback;
import com.neurowvu.rehabilitationapp.entity.Grade;
import com.neurowvu.rehabilitationapp.entity.Patient;
import com.neurowvu.rehabilitationapp.entity.Prescription;
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
}
