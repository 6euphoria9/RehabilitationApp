package com.neurowvu.rehabilitationapp.mapper;

import com.neurowvu.rehabilitationapp.dto.DoctorDTO;
import com.neurowvu.rehabilitationapp.entity.Doctor;
import com.neurowvu.rehabilitationapp.dto.RegistrationDoctorForm;
import org.springframework.stereotype.Component;

@Component
public class DoctorMapper {

    public Doctor mapToDoctor(DoctorDTO doctorDTO) {
        return new Doctor()
                .setFirstName(doctorDTO.getFirstName())
                .setLastName(doctorDTO.getLastName());
    }

    public DoctorDTO mapToDoctorDTO(Doctor doctor) {
        return new DoctorDTO()
                .setFirstName(doctor.getFirstName())
                .setLastName(doctor.getLastName());
    }

    public Doctor formToDoctor(RegistrationDoctorForm form) {
        return new Doctor()
                .setFirstName(form.getFirstName())
                .setLastName(form.getLastName());
    }

}