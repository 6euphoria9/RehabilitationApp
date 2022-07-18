package com.neurowvu.rehabilitationapp.controllers;

import com.neurowvu.rehabilitationapp.dto.DoctorDTO;
import com.neurowvu.rehabilitationapp.dto.RegistrationPatientForm;
import com.neurowvu.rehabilitationapp.entity.Doctor;
import com.neurowvu.rehabilitationapp.mapper.DoctorMapper;
import com.neurowvu.rehabilitationapp.dto.RegistrationDoctorForm;
import com.neurowvu.rehabilitationapp.services.DoctorService;
import com.neurowvu.rehabilitationapp.services.PatientService;
import com.neurowvu.rehabilitationapp.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/auth")
public class AuthController {
    private final UserService userService;
    private final DoctorMapper doctorMapper;
    private final DoctorService doctorService;
    private final PatientService patientService;

    @Autowired
    public AuthController(UserService userService, DoctorMapper doctorMapper, DoctorService doctorService, PatientService patientService) {
        this.userService = userService;
        this.doctorMapper = doctorMapper;
        this.doctorService = doctorService;
        this.patientService = patientService;
    }

    @GetMapping("/login")
    public String login(){
        return "login";
    }

    @GetMapping("/success")
    public String success(){
        return "success";
    }


//    @GetMapping
//    public String registerForm(){
//        return "registration";
//    }

    @GetMapping("/registration/patient")
    public String registrationPatient(@ModelAttribute("form")RegistrationPatientForm form, Model model) {
        List<DoctorDTO> doctors = doctorService.getAll()
                .stream()
                .map(doctorMapper::mapToDoctorDTO)
                .toList();

        model.addAttribute("doctors", doctors);

        return "registrationPatient";
    }

    @PostMapping("/registration/patient")
    public String registrationPatientDone(@ModelAttribute("form") RegistrationPatientForm form) {
        patientService.registration(form);
        return "redirect:/auth/login";
    }

    @GetMapping("/registration/doctor")
    public String registrationDoctor(@ModelAttribute("form") RegistrationDoctorForm form) {
        return "registrationDoctor";
    }
    @PostMapping("/registration/doctor")
    public String registrationDoctorDone(@ModelAttribute("form") RegistrationDoctorForm form) {//todo здесь надо будет из формы вытягивать инфу и лепить доктора
        doctorService.registration(form);
        return "redirect:/auth/login";
    }
}
