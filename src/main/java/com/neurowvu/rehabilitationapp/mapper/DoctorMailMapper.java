package com.neurowvu.rehabilitationapp.mapper;

import com.neurowvu.rehabilitationapp.dto.DoctorMailDTO;
import com.neurowvu.rehabilitationapp.entity.DoctorMail;
import com.neurowvu.rehabilitationapp.entity.Patient;
import org.springframework.stereotype.Component;

@Component
public class DoctorMailMapper {

    public DoctorMailDTO mapMailToDTO(DoctorMail doctorMail) {
        System.out.println("mapper started, doctor mail has: " + doctorMail.getFeedback().getId());
        DoctorMailDTO doctorMailDTO = new DoctorMailDTO();

        Patient patient = doctorMail.getFeedback().getPatient();
        doctorMailDTO.setFeedbackId(doctorMail.getFeedback().getId());
        doctorMailDTO.setPatientName(patient.getFirstName() + " " + patient.getLastName());

        return doctorMailDTO;
    }
}
