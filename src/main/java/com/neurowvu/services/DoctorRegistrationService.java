package com.neurowvu.services;

import com.neurowvu.dto.DoctorDTO;
import com.neurowvu.entity.Doctor;

public interface DoctorRegistrationService {

    Doctor registration(DoctorDTO doctorDTO);

}
