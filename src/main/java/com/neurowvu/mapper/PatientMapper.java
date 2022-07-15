package com.neurowvu.mapper;

import com.neurowvu.dto.PatientDTO;
import com.neurowvu.entity.Patient;
import org.springframework.stereotype.Component;

@Component
public class PatientMapper {


    public PatientDTO mapToPatientDTO(Patient patient) {
        return new PatientDTO()
                .setFirstName(patient.getFirstName())
                .setLastName(patient.getLastName())
                .setAddress(patient.getAddress())
                .setCity(patient.getCity());
    }

}
