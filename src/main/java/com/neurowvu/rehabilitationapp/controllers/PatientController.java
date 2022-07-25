package com.neurowvu.rehabilitationapp.controllers;

import com.neurowvu.rehabilitationapp.dto.*;
import com.neurowvu.rehabilitationapp.entity.Feedback;
import com.neurowvu.rehabilitationapp.entity.Prescription;
import com.neurowvu.rehabilitationapp.entity.User;
import com.neurowvu.rehabilitationapp.mapper.*;
import com.neurowvu.rehabilitationapp.security.SecurityUser;
import com.neurowvu.rehabilitationapp.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@Controller
@RequestMapping("/patient")
public class PatientController {

    private final AssignmentMapper assignmentMapper;
    private final PatientMapper patientMapper;
    private final DoctorMapper doctorMapper;
    private final PatientMailService patientMailService;
    private final DoctorMailService doctorMailService;
    private final PrescriptionService prescriptionService;
    private final FeedbackMapper feedbackMapper;
    private final FeedbackService feedbackService;
    private final GradeService gradeService;
    private final GradeHistoryMapper gradeHistoryMapper;


    @Autowired
    public PatientController(AssignmentMapper assignmentMapper, PatientMapper patientMapper, DoctorMapper doctorMapper,
                             PatientMailService patientMailService, DoctorMailService doctorMailService,
                             PrescriptionService prescriptionService, FeedbackMapper feedbackMapper, FeedbackService feedbackService, GradeService gradeService,
                             GradeHistoryMapper gradeHistoryMapper) {
        this.assignmentMapper = assignmentMapper;
        this.patientMapper = patientMapper;
        this.doctorMapper = doctorMapper;
        this.patientMailService = patientMailService;
        this.doctorMailService = doctorMailService;
        this.prescriptionService = prescriptionService;
        this.feedbackMapper = feedbackMapper;
        this.feedbackService = feedbackService;
        this.gradeService = gradeService;
        this.gradeHistoryMapper = gradeHistoryMapper;
    }

    @GetMapping("/cabinet")
    public String patientCabinet(Model model) {
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
    public String taskInformation(@PathVariable("id") Long id, Model model) {

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
    public String feedback(@PathVariable("id") Long id, @ModelAttribute("form") AssignmentDTO form, Model model) {
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
    public String sendFeedback(@ModelAttribute("form") AssignmentDTO form, Model model) {
        System.out.println(form.getComment() + "LALALALALALAALLAL");
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

    @GetMapping("/gradehistory")
    public String getGradeHistory(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        SecurityUser personDetails = (SecurityUser) authentication.getPrincipal();
        User user = personDetails.getUser();

        PatientDTO patientDTO = patientMapper.mapToPatientDTO(user.getPatient());
        model.addAttribute("user", patientDTO);

        List<GradeHistoryDTO> historyDTOList = new ArrayList<>();

        List<Feedback> feedbacks = feedbackService.getAllById(patientDTO.getId());

        for (Feedback fb : feedbacks) {
            historyDTOList.add(gradeHistoryMapper.mapToGradeHistoryDTO(fb, gradeService.getByPrescriptionId(fb.getPrescription().getId())));
        }

        historyDTOList.sort(Comparator.comparing(GradeHistoryDTO::getDate));

        model.addAttribute("histor", historyDTOList);

        return "patient/gradeHistory";
    }

    @GetMapping("/analyze/{id}")
    public String analyzing(@PathVariable("id") Long id, Model model) {
        FeedbackDTO feedback = feedbackMapper.mapFeedbackToDTO(feedbackService.getById(id));
        model.addAttribute("feedback", feedback);

        return "/patient/diagram";
    }

}
