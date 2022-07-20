package com.neurowvu.rehabilitationapp.controllers;

import com.neurowvu.rehabilitationapp.dto.DoctorDTO;
import com.neurowvu.rehabilitationapp.dto.RegistrationPatientForm;
import com.neurowvu.rehabilitationapp.entity.User;
import com.neurowvu.rehabilitationapp.mapper.DoctorMapper;
import com.neurowvu.rehabilitationapp.dto.RegistrationDoctorForm;
import com.neurowvu.rehabilitationapp.services.DoctorService;
import com.neurowvu.rehabilitationapp.services.PatientService;
import com.neurowvu.rehabilitationapp.services.UserService;
import com.neurowvu.rehabilitationapp.util.UserValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/auth")
public class AuthController {
    private final UserService userService;
    private final DoctorMapper doctorMapper;
    private final DoctorService doctorService;
    private final PatientService patientService;
    private final UserValidator userValidator;

    @Autowired
    public AuthController(UserService userService, DoctorMapper doctorMapper, DoctorService doctorService, PatientService patientService, UserValidator userValidator) {
        this.userService = userService;
        this.doctorMapper = doctorMapper;
        this.doctorService = doctorService;
        this.patientService = patientService;
        this.userValidator = userValidator;
    }

    @GetMapping("/login")
    public String login(){
        return "login";
    }

//    @GetMapping("/success")
//    public String success(){
//        return "success";
//    }

    @GetMapping("/success")
    public String successRedirect() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.getUserByUsername(auth.getName()).get();
        return user.getRole().equals("ROLE_DOCTOR") ? "redirect:/doctor/cabinet" : "redirect:/patient/cabinet";
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
        System.out.println("DOCTORS");
        doctors.forEach(System.out::println);
        model.addAttribute("doctors", doctors);

        return "registrationPatient";
    }

    @PostMapping("/registration/patient")
    public String registrationPatientDone(@ModelAttribute("form") RegistrationPatientForm form, BindingResult bindingResult) {
        System.out.println(form);

        userValidator.validate(form, bindingResult);

        if (bindingResult.hasErrors())
            return "registrationPatient";


        patientService.registration(form);
        return "redirect:/auth/login";
    }

    @GetMapping("/registration/doctor")
    public String registrationDoctor(@ModelAttribute("form") RegistrationDoctorForm form) {
        return "registrationDoctor";
    }
    @PostMapping("/registration/doctor")
    public String registrationDoctorDone(@Valid @ModelAttribute("form") RegistrationDoctorForm form, BindingResult bindingResult) {

        userValidator.validate(form, bindingResult);

        if (bindingResult.hasErrors())
            return "registrationDoctor";

        doctorService.registration(form);
        return "redirect:/auth/login";
    }

//    @PostMapping
//    public String processRegistration(RegistrationForm form) {
//        //userRepository.save(form.toUser(passwordEncoder));
//        return "redirect:/login";
//    }
}
