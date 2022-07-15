package com.neurowvu.services;

import com.neurowvu.entity.Patient;

import java.util.List;

public interface DoctorService {

    List<Patient> getPatientListByDoctorId(Long doctorId);
}
