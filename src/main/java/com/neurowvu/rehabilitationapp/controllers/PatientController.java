package com.neurowvu.rehabilitationapp.controllers;

import com.neurowvu.rehabilitationapp.dto.AssignmentDTO;
import com.neurowvu.rehabilitationapp.dto.DoctorDTO;
import com.neurowvu.rehabilitationapp.dto.PatientDTO;
import com.neurowvu.rehabilitationapp.entity.Doctor;
import com.neurowvu.rehabilitationapp.entity.Prescription;
import com.neurowvu.rehabilitationapp.entity.User;
import com.neurowvu.rehabilitationapp.mapper.AssignmentMapper;
import com.neurowvu.rehabilitationapp.mapper.DoctorMapper;
import com.neurowvu.rehabilitationapp.mapper.PatientMapper;
import com.neurowvu.rehabilitationapp.security.SecurityUser;
import com.neurowvu.rehabilitationapp.services.PatientMailService;
import com.neurowvu.rehabilitationapp.services.PatientService;
import com.neurowvu.rehabilitationapp.services.PrescriptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/patient")
public class PatientController {

    private final AssignmentMapper assignmentMapper;
    private final PatientMapper patientMapper;
    private final DoctorMapper doctorMapper;
    private final PatientService patientService;
    private final PatientMailService patientMailService;
    private final PrescriptionService prescriptionService;

    @Autowired
    public PatientController(AssignmentMapper assignmentMapper, PatientMapper patientMapper, DoctorMapper doctorMapper, PatientService patientService, PatientMailService patientMailService, PrescriptionService prescriptionService) {
        this.assignmentMapper = assignmentMapper;
        this.patientMapper = patientMapper;
        this.doctorMapper = doctorMapper;
        this.patientService = patientService;
        this.patientMailService = patientMailService;
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

        AssignmentDTO assignment = new AssignmentDTO();

        if (isThereMessage) {
            Prescription prescription = prescriptionService.getById(patientMailService.getPrescriptionId(patientDTO.getId()));
            assignment = assignmentMapper.mapPrescriptionToForm(prescription);
        }

        model.addAttribute("form", assignment);

        DoctorDTO doctor = doctorMapper.mapToDoctorDTO(user.getPatient().getDoctor());
        model.addAttribute("doctor", doctor);

        return "patient/cabinet";
    }

}
