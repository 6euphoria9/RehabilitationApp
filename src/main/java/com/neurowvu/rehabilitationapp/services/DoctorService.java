package com.neurowvu.rehabilitationapp.services;

import com.neurowvu.rehabilitationapp.entity.Doctor;
import com.neurowvu.rehabilitationapp.entity.Patient;
import com.neurowvu.rehabilitationapp.entity.User;
import com.neurowvu.rehabilitationapp.mapper.DoctorMapper;
import com.neurowvu.rehabilitationapp.mapper.UserMapper;
import com.neurowvu.rehabilitationapp.repositories.DoctorsRepository;
import com.neurowvu.rehabilitationapp.repositories.PatientsRepository;
import com.neurowvu.rehabilitationapp.repositories.UsersRepository;
import com.neurowvu.rehabilitationapp.dto.RegistrationDoctorForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DoctorService {
    private final DoctorsRepository doctorsRepository;
    private final PatientsRepository patientsRepository;
    private final DoctorMapper doctorMapper;
    private final UsersRepository usersRepository;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;
    @Autowired
    public DoctorService(DoctorsRepository doctorsRepository, PatientsRepository patientsRepository, DoctorMapper doctorMapper, UsersRepository usersRepository, UserMapper userMapper, PasswordEncoder passwordEncoder) {
        this.doctorsRepository = doctorsRepository;
        this.patientsRepository = patientsRepository;
        this.doctorMapper = doctorMapper;
        this.usersRepository = usersRepository;
        this.userMapper = userMapper;
        this.passwordEncoder = passwordEncoder;
    }

    public List<Patient> getPatientListByDoctorId(Long doctorId) {
        return patientsRepository.getAllByDoctorId(doctorId);
    }

    public Doctor getById(Long id) {
        return doctorsRepository.getById(id);
    }
    public List<Doctor> getAll() {
        return doctorsRepository.findAll();
    }

    public void registration(RegistrationDoctorForm form) {
       User user = userMapper.mapDoctorFormToUser(form);
       user.setPassword(passwordEncoder.encode(user.getPassword()));
       usersRepository.save(user);
        Doctor doctor = doctorMapper.formToDoctor(form);
        doctor.setUser(user);
        doctorsRepository.save(doctor);
    }
}
