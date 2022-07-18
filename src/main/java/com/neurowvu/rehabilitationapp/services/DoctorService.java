package com.neurowvu.rehabilitationapp.services;

import com.neurowvu.rehabilitationapp.entity.Doctor;
import com.neurowvu.rehabilitationapp.entity.Patient;
import com.neurowvu.rehabilitationapp.entity.User;
import com.neurowvu.rehabilitationapp.mapper.DoctorMapper;
import com.neurowvu.rehabilitationapp.mapper.UserMapper;
import com.neurowvu.rehabilitationapp.repositories.DoctorRepository;
import com.neurowvu.rehabilitationapp.repositories.PatientRepository;
import com.neurowvu.rehabilitationapp.repositories.UserRepository;
import com.neurowvu.rehabilitationapp.dto.RegistrationDoctorForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DoctorService {
    private final DoctorRepository doctorRepository;
    private final PatientRepository patientRepository;
    private final DoctorMapper doctorMapper;
    private final UserRepository userRepository;
    private final UserMapper userMapper;
    @Autowired
    public DoctorService(DoctorRepository doctorRepository, PatientRepository patientRepository, DoctorMapper doctorMapper, UserRepository userRepository, UserMapper userMapper) {
        this.doctorRepository = doctorRepository;
        this.patientRepository = patientRepository;
        this.doctorMapper = doctorMapper;
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }

    public List<Patient> getPatientListByDoctorId(Long doctorId) {
        return patientRepository.getAllByDoctorId(doctorId);
    }

    public Doctor getById(Long id) {
        return doctorRepository.getById(id);
    }
    public List<Doctor> getAll() {
        return doctorRepository.findAll();
    }

    public void registration(RegistrationDoctorForm form) {
       User user = userMapper.mapDoctorFormToUser(form);
       userRepository.save(user);
        Doctor doctor = doctorMapper.formToDoctor(form);
        doctor.setUser(user);
        doctorRepository.save(doctor);
    }
}
