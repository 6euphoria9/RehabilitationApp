package com.neurowvu.rehabilitationapp.controllers;

import com.neurowvu.rehabilitationapp.dto.PatientDTO;
import com.neurowvu.rehabilitationapp.entity.Doctor;
import com.neurowvu.rehabilitationapp.entity.User;
import com.neurowvu.rehabilitationapp.mapper.DoctorMapper;
import com.neurowvu.rehabilitationapp.mapper.PatientMapper;
import com.neurowvu.rehabilitationapp.security.SecurityUser;
import com.neurowvu.rehabilitationapp.services.DoctorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/doctor")
public class DoctorController {

    private final DoctorService doctorService;
    private final PatientMapper patientMapper;
    private final DoctorMapper doctorMapper;

    @Autowired
    public DoctorController(DoctorService doctorService, PatientMapper patientMapper, DoctorMapper doctorMapper) {
        this.doctorService = doctorService;
        this.patientMapper = patientMapper;
        this.doctorMapper = doctorMapper;
    }

    @GetMapping("/cabinet")
    public String doctorsCabinet(Model model){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        SecurityUser personDetails = (SecurityUser) authentication.getPrincipal();
        User user = personDetails.getUser();

        Doctor doctor = user.getDoctor();
        model.addAttribute("doctor_name",
                doctor.getFirstName() + " " + doctor.getLastName());

        List<PatientDTO> patients = doctor.getPatientList().stream().map(patientMapper::mapToPatientDTO).collect(Collectors.toList());
        model.addAttribute("patients", patients);

        return "doctor/cabinet";
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
