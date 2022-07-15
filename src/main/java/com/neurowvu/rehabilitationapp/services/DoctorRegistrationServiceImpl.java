package com.neurowvu.services;

import com.neurowvu.dto.DoctorDTO;
import com.neurowvu.entity.Doctor;
import com.neurowvu.mapper.DoctorMapper;
import com.neurowvu.repositories.DoctorRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class DoctorRegistrationServiceImpl implements DoctorRegistrationService {

    private final DoctorRepository doctorRepository;
    private final DoctorMapper doctorMapper;

    public DoctorRegistrationServiceImpl(DoctorRepository doctorRepository, DoctorMapper doctorMapper) {
        this.doctorRepository = doctorRepository;
        this.doctorMapper = doctorMapper;
    }

    @Override
    public Doctor registration(DoctorDTO doctorDTO) {
        return doctorRepository.save(doctorMapper.mapToDoctor(doctorDTO));
    }


}
