package com.neurowvu.rehabilitationapp.controllers;

import com.neurowvu.rehabilitationapp.dto.PatientDTO;
import com.neurowvu.rehabilitationapp.mapper.PatientMapper;
import com.neurowvu.rehabilitationapp.services.DoctorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/doctor")
public class DoctorController {

    private final DoctorService doctorService;
    private final PatientMapper patientMapper;

    @Autowired
    public DoctorController(DoctorService doctorService, PatientMapper patientMapper) {
        this.doctorService = doctorService;
        this.patientMapper = patientMapper;
    }

    @GetMapping("/cabinet")
    public String doctorsCabinet(){
        return "doctor/cabinet";
    }

    @PostMapping("/registrate")
    public String registration() {//todo здесь надо будет из формы вытягивать инфу и лепить доктора
        //doctorService.registration(doctorDTO);
        return "login";
    }

    @GetMapping("/doctor/{doctorId}/patient")
    public List<PatientDTO> getPatientList(@PathVariable Long doctorId) {
        List<PatientDTO> patients = doctorService.getPatientListByDoctorId(doctorId)
                .stream()
                .map(patientMapper::mapToPatientDTO)
                .collect(Collectors.toList());
        return null;//todo тут редирект на.. кабинет доктора?
    }

}
