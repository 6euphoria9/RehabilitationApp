package com.neurowvu.rehabilitationapp.controllers;

import com.neurowvu.rehabilitationapp.dto.AssignmentDTO;
import com.neurowvu.rehabilitationapp.dto.DoctorDTO;
import com.neurowvu.rehabilitationapp.dto.PatientDTO;
import com.neurowvu.rehabilitationapp.entity.Prescription;
import com.neurowvu.rehabilitationapp.entity.User;
import com.neurowvu.rehabilitationapp.mapper.AssignmentMapper;
import com.neurowvu.rehabilitationapp.mapper.DoctorMapper;
import com.neurowvu.rehabilitationapp.mapper.PatientMapper;
import com.neurowvu.rehabilitationapp.security.SecurityUser;
import com.neurowvu.rehabilitationapp.services.DoctorMailService;
import com.neurowvu.rehabilitationapp.services.PatientMailService;
import com.neurowvu.rehabilitationapp.services.PatientService;
import com.neurowvu.rehabilitationapp.services.PrescriptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/patient")
public class PatientController {

    private final AssignmentMapper assignmentMapper;
    private final PatientMapper patientMapper;
    private final DoctorMapper doctorMapper;
    private final PatientService patientService;
    private final PatientMailService patientMailService;
    private final DoctorMailService doctorMailService;
    private final PrescriptionService prescriptionService;

    @Autowired
    public PatientController(AssignmentMapper assignmentMapper, PatientMapper patientMapper, DoctorMapper doctorMapper, PatientService patientService, PatientMailService patientMailService, DoctorMailService doctorMailService, PrescriptionService prescriptionService) {
        this.assignmentMapper = assignmentMapper;
        this.patientMapper = patientMapper;
        this.doctorMapper = doctorMapper;
        this.patientService = patientService;
        this.patientMailService = patientMailService;
        this.doctorMailService = doctorMailService;
        this.prescriptionService = prescriptionService;
    }

    @GetMapping("/cabinet")
    public String patientCabinet(Model model){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        SecurityUser personDetails = (SecurityUser) authentication.getPrincipal();
        User user = personDetails.getUser();

        PatientDTO patientDTO = patientMapper.mapToPatientDTO(user.getPatient());
        model.addAttribute("user", patientDTO);

        Boolean isThereMessage = patientMailService.isThereAMessage(patientDTO.getId());
        model.addAttribute("isThereMessage", isThereMessage);
        System.out.println(isThereMessage);


        List<AssignmentDTO> assignment = new ArrayList<>();

        if (isThereMessage) {
            List<Prescription> prescription = patientMailService.getPrescriptionsByThePatientId(patientDTO.getId());

            for (Prescription pr : prescription) {
                assignment.add(assignmentMapper.mapPrescriptionToForm(pr));
            }
        }

        model.addAttribute("form", assignment);

        DoctorDTO doctor = doctorMapper.mapToDoctorDTO(user.getPatient().getDoctor());
        model.addAttribute("doctor", doctor);

        return "patient/entry";
    }

    @GetMapping("/cabinet/{id}")
    public String taskInformation(@PathVariable("id")Long id, Model model){

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        SecurityUser personDetails = (SecurityUser) authentication.getPrincipal();
        User user = personDetails.getUser();

        PatientDTO patientDTO = patientMapper.mapToPatientDTO(user.getPatient());
        model.addAttribute("user", patientDTO);

        AssignmentDTO assignment = assignmentMapper.mapPrescriptionToForm(prescriptionService.getById(id));
        model.addAttribute("form", assignment);

        DoctorDTO doctor = doctorMapper.mapToDoctorDTO(user.getPatient().getDoctor());
        model.addAttribute("doctor", doctor);

        return "patient/cabinet";
    }


    @PostMapping("/feedback/{id}")
    public String feedback(@PathVariable("id")Long id, @ModelAttribute("form")AssignmentDTO form, Model model){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        SecurityUser personDetails = (SecurityUser) authentication.getPrincipal();
        User user = personDetails.getUser();
        PatientDTO patientDTO = patientMapper.mapToPatientDTO(user.getPatient());
        model.addAttribute("user", patientDTO);

        Prescription prescription = prescriptionService.getById(id);
        AssignmentDTO assignment = assignmentMapper.mapPrescriptionToForm(prescription);
        model.addAttribute("form", assignment);
        System.out.println(assignment);

        AssignmentDTO feedback = new AssignmentDTO();
        model.addAttribute("feedback", feedback);


        DoctorDTO doctor = doctorMapper.mapToDoctorDTO(user.getPatient().getDoctor());
        model.addAttribute("doctor", doctor);

        return "patient/feedback";
    }

    @PostMapping("/feedback/send")
    public String sendFeedback(@ModelAttribute("form")AssignmentDTO form, Model model) {
    System.out.println(form);
    patientMailService.removeMailByPrescriptionId(form.getId());
    doctorMailService.sendMailToDoctor(form);



        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        SecurityUser personDetails = (SecurityUser) authentication.getPrincipal();
        User user = personDetails.getUser();

        PatientDTO patientDTO = patientMapper.mapToPatientDTO(user.getPatient());
        model.addAttribute("user", patientDTO);

        Boolean isThereMessage = patientMailService.isThereAMessage(patientDTO.getId());
        model.addAttribute("isThereMessage", isThereMessage);
        System.out.println(isThereMessage);


        List<AssignmentDTO> assignment = new ArrayList<>();

        if (isThereMessage) {
            List<Prescription> prescription = patientMailService.getPrescriptionsByThePatientId(patientDTO.getId());

            for (Prescription pr : prescription) {
                assignment.add(assignmentMapper.mapPrescriptionToForm(pr));
            }
        }

        model.addAttribute("form", assignment);

        DoctorDTO doctor = doctorMapper.mapToDoctorDTO(user.getPatient().getDoctor());
        model.addAttribute("doctor", doctor);


        return "patient/entry";
    }

}
