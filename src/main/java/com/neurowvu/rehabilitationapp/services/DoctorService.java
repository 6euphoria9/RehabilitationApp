package com.neurowvu.rehabilitationapp.services;

import com.neurowvu.rehabilitationapp.dto.DoctorDTO;
import com.neurowvu.rehabilitationapp.entity.Patient;
import com.neurowvu.rehabilitationapp.mapper.DoctorMapper;
import com.neurowvu.rehabilitationapp.repositories.DoctorRepository;
import com.neurowvu.rehabilitationapp.repositories.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DoctorService {
    private final DoctorRepository doctorRepository;
    private final PatientRepository patientRepository;
    private final DoctorMapper doctorMapper;
    @Autowired
    public DoctorService(DoctorRepository doctorRepository, PatientRepository patientRepository, DoctorMapper doctorMapper) {
        this.doctorRepository = doctorRepository;
        this.patientRepository = patientRepository;
        this.doctorMapper = doctorMapper;
    }

    public List<Patient> getPatientListByDoctorId(Long doctorId) {
        return patientRepository.getAllByDoctorId(doctorId);
    }

    public void registration(DoctorDTO doctorDTO) {
        doctorRepository.save(doctorMapper.mapToDoctor(doctorDTO));
    }
}
