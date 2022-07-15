package com.neurowvu.services;

import com.neurowvu.entity.Patient;
import com.neurowvu.repositories.DoctorRepository;
import com.neurowvu.repositories.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DoctorServiceImpl implements DoctorService{

    private final DoctorRepository doctorRepository;
    private final PatientRepository patientRepository;

    @Autowired
    public DoctorServiceImpl(DoctorRepository doctorRepository, PatientRepository patientRepository) {
        this.doctorRepository = doctorRepository;
        this.patientRepository = patientRepository;
    }

    @Override
    public List<Patient> getPatientListByDoctorId(Long doctorId) {
        return patientRepository.getAllByDoctorId(doctorId);
    }
}
