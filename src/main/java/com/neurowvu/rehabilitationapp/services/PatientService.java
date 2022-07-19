package com.neurowvu.rehabilitationapp.services;

import com.neurowvu.rehabilitationapp.dto.RegistrationPatientForm;
import com.neurowvu.rehabilitationapp.entity.Doctor;
import com.neurowvu.rehabilitationapp.entity.Patient;
import com.neurowvu.rehabilitationapp.entity.User;
import com.neurowvu.rehabilitationapp.mapper.DoctorMapper;
import com.neurowvu.rehabilitationapp.mapper.PatientMapper;
import com.neurowvu.rehabilitationapp.mapper.UserMapper;
import com.neurowvu.rehabilitationapp.repositories.DoctorRepository;
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
    private final DoctorRepository doctorRepository;

    @Autowired
    public PatientService(PatientRepository patientRepository, UserRepository userRepository, UserMapper userMapper, PatientMapper patientMapper, DoctorMapper doctorMapper, DoctorRepository doctorRepository) {
        this.patientRepository = patientRepository;
        this.userRepository = userRepository;
        this.userMapper = userMapper;
        this.patientMapper = patientMapper;
        this.doctorMapper = doctorMapper;
        this.doctorRepository = doctorRepository;
    }

    public void registration(RegistrationPatientForm form) {
        User user = userMapper.mapPatientFormToUser(form);
        userRepository.save(user);
        Patient patient = patientMapper.formToPatient(form);
        patient.setUser(user);
        Doctor doctor = doctorRepository.getById(form.getDoctorId());
        patient.setDoctor(doctor);
        patientRepository.save(patient);
    }

    public Patient getById(Long id) {
        return patientRepository.findById(id).get();
    }
}
