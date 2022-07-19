package com.neurowvu.rehabilitationapp.mapper;

import com.neurowvu.rehabilitationapp.dto.PatientDTO;
import com.neurowvu.rehabilitationapp.dto.RegistrationPatientForm;
import com.neurowvu.rehabilitationapp.entity.Patient;
import com.neurowvu.rehabilitationapp.services.DoctorService;
import org.springframework.stereotype.Component;

@Component
public class PatientMapper {

    private final DoctorService doctorService;

    public PatientMapper(DoctorService doctorService) {
        this.doctorService = doctorService;
    }


    public PatientDTO mapToPatientDTO(Patient patient) {
        return new PatientDTO()
                .setFirstName(patient.getFirstName())
                .setLastName(patient.getLastName())
                .setAddress(patient.getAddress())
                .setCity(patient.getCity());
    }

    public Patient formToPatient(RegistrationPatientForm form) {
        return new Patient()
                .setFirstName(form.getFirstName())
                .setLastName(form.getLastName())
                .setAddress(form.getAddress())
                .setCity(form.getCity());
    }

}
