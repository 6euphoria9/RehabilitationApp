package com.neurowvu.rehabilitationapp.services;

import com.neurowvu.rehabilitationapp.dto.DoctorDTO;
import com.neurowvu.rehabilitationapp.dto.RegistrationPatientForm;
import com.neurowvu.rehabilitationapp.entity.Patient;
import com.neurowvu.rehabilitationapp.entity.User;
import com.neurowvu.rehabilitationapp.mapper.DoctorMapper;
import com.neurowvu.rehabilitationapp.mapper.PatientMapper;
import com.neurowvu.rehabilitationapp.mapper.UserMapper;
import com.neurowvu.rehabilitationapp.repositories.PatientRepository;
import com.neurowvu.rehabilitationapp.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PatientService {

    private final PatientRepository patientRepository;
    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final PatientMapper patientMapper;
    private final DoctorMapper doctorMapper;

    @Autowired
    public PatientService(PatientRepository patientRepository, UserRepository userRepository, UserMapper userMapper, PatientMapper patientMapper, DoctorMapper doctorMapper) {
        this.patientRepository = patientRepository;
        this.userRepository = userRepository;
        this.userMapper = userMapper;
        this.patientMapper = patientMapper;
        this.doctorMapper = doctorMapper;
    }

    public void registration(RegistrationPatientForm form) {
        User user = userMapper.mapPatientFormToUser(form);
        userRepository.save(user);
        Patient patient = patientMapper.formToPatient(form);
        patient.setUser(user);
        patientRepository.save(patient);
    }
}
