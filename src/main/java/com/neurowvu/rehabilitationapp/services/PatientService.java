package com.neurowvu.rehabilitationapp.services;

import com.neurowvu.rehabilitationapp.dto.RegistrationPatientForm;
import com.neurowvu.rehabilitationapp.entity.Doctor;
import com.neurowvu.rehabilitationapp.entity.Patient;
import com.neurowvu.rehabilitationapp.entity.User;
import com.neurowvu.rehabilitationapp.mapper.DoctorMapper;
import com.neurowvu.rehabilitationapp.mapper.PatientMapper;
import com.neurowvu.rehabilitationapp.mapper.UserMapper;
import com.neurowvu.rehabilitationapp.repositories.DoctorsRepository;
import com.neurowvu.rehabilitationapp.repositories.PatientsRepository;
import com.neurowvu.rehabilitationapp.repositories.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PatientService {

    private final PatientsRepository patientsRepository;
    private final UsersRepository usersRepository;
    private final UserMapper userMapper;
    private final PatientMapper patientMapper;
    private final DoctorMapper doctorMapper;
    private final DoctorsRepository doctorsRepository;

    @Autowired
    public PatientService(PatientsRepository patientsRepository, UsersRepository usersRepository, UserMapper userMapper, PatientMapper patientMapper, DoctorMapper doctorMapper, DoctorsRepository doctorsRepository) {
        this.patientsRepository = patientsRepository;
        this.usersRepository = usersRepository;
        this.userMapper = userMapper;
        this.patientMapper = patientMapper;
        this.doctorMapper = doctorMapper;
        this.doctorsRepository = doctorsRepository;
    }

    public void registration(RegistrationPatientForm form) {
        User user = userMapper.mapPatientFormToUser(form);
        usersRepository.save(user);
        Patient patient = patientMapper.formToPatient(form);
        patient.setUser(user);
        Doctor doctor = doctorsRepository.getById(form.getDoctorId());
        patient.setDoctor(doctor);
        patientsRepository.save(patient);
    }

    public Patient getById(Long id) {
        return patientsRepository.findById(id).get();
    }
}
