package com.neurowvu.rehabilitationapp.mapper;

import com.neurowvu.rehabilitationapp.dto.PatientMailDTO;
import com.neurowvu.rehabilitationapp.entity.PatientMail;
import org.springframework.stereotype.Component;


@Component
public class PatientMailMapper {

    public PatientMailDTO mapMailToDTO(PatientMail patientMail) {
        System.out.println("MAPPER WORKING: " + patientMail.getPatient().getId() + " " + patientMail.getPrescription().getId());
        PatientMailDTO patientMailDTO = new PatientMailDTO();
        patientMailDTO.setId(patientMail.getId());
        patientMailDTO.setPatientId(patientMail.getPatient().getId());
        patientMailDTO.setPrescriptionId(patientMail.getPrescription().getId());
        return patientMailDTO;
    }
}
