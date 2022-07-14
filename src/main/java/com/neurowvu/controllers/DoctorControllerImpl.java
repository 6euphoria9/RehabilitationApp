package com.neurowvu.controllers;

import com.neurowvu.dto.DoctorDTO;
import com.neurowvu.dto.PatientDTO;
import com.neurowvu.entity.Doctor;
import com.neurowvu.mapper.DoctorMapper;
import com.neurowvu.mapper.PatientMapper;
import com.neurowvu.services.DoctorRegistrationService;
import com.neurowvu.services.DoctorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
public class DoctorControllerImpl implements DoctorController{

    private final DoctorRegistrationService doctorRegistrationService;
    private final DoctorService doctorService;
    private final DoctorMapper doctorMapper;
    private final PatientMapper patientMapper;

    @Autowired
    public DoctorControllerImpl(DoctorRegistrationService doctorRegistrationService, DoctorService doctorService, DoctorMapper doctorMapper, PatientMapper patientMapper) {
        this.doctorRegistrationService = doctorRegistrationService;
        this.doctorService = doctorService;
        this.doctorMapper = doctorMapper;
        this.patientMapper = patientMapper;
    }

    @PostMapping("/registrate")
    public DoctorDTO registration(@RequestBody DoctorDTO doctorDTO) {
        Doctor doctor = doctorRegistrationService.registration(doctorDTO);
        return doctorMapper.mapToDoctorDTO(doctor);
    }

    @GetMapping("/doctor/{doctorId}/patient")
    public List<PatientDTO> getPatientList(@PathVariable Long doctorId) {
        return doctorService.getPatientListByDoctorId(doctorId)
                .stream()
                .map(patientMapper::mapToPatientDTO)
                .collect(Collectors.toList());
    }

}
