package com.neurowvu.rehabilitationapp.controllers;

import com.neurowvu.rehabilitationapp.dto.*;
import com.neurowvu.rehabilitationapp.entity.*;
import com.neurowvu.rehabilitationapp.mapper.*;
import com.neurowvu.rehabilitationapp.security.SecurityUser;
import com.neurowvu.rehabilitationapp.services.*;
import com.neurowvu.rehabilitationapp.services.TaskService;
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
    private final PatientService patientService;
    private final PatientMapper patientMapper;
    private final DoctorMapper doctorMapper;
    private final AssignmentMapper assignmentMapper;
    private final TaskMapper taskMapper;
    private final TaskService taskService;
    private final PrescriptionService prescriptionService;
    private final DoctorMailService doctorMailService;
    private final DoctorMailMapper doctorMailMapper;
    private final FeedbackService feedbackService;
    private final GradeService gradeService;

    @Autowired
    public DoctorController(DoctorService doctorService, PatientService patientService, PatientMapper patientMapper, DoctorMapper doctorMapper, AssignmentMapper assignmentMapper, TaskMapper taskMapper, TaskService taskService, PrescriptionService prescriptionService, DoctorMailService doctorMailService, DoctorMailMapper doctorMailMapper, FeedbackService feedbackService, GradeService gradeService) {
        this.doctorService = doctorService;
        this.patientService = patientService;
        this.patientMapper = patientMapper;
        this.doctorMapper = doctorMapper;
        this.assignmentMapper = assignmentMapper;
        this.taskMapper = taskMapper;
        this.taskService = taskService;
        this.prescriptionService = prescriptionService;
        this.doctorMailService = doctorMailService;
        this.doctorMailMapper = doctorMailMapper;
        this.feedbackService = feedbackService;
        this.gradeService = gradeService;
    }

    @GetMapping("/cabinet")
    public String doctorsCabinet(Model model){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        SecurityUser personDetails = (SecurityUser) authentication.getPrincipal();
        User user = personDetails.getUser();

        Doctor doctor = user.getDoctor();
        model.addAttribute("doctor_name", doctor.getLastName());

        Boolean isThereMessage = doctorMailService.isThereAMessage(doctor.getId());
        model.addAttribute("isThereMessage", isThereMessage);
        System.out.println(isThereMessage);
        if (isThereMessage) {
            List<DoctorMailDTO> mails = doctorMailService.getAllMessagesByDoctorId(doctor.getId()).stream().map(doctorMailMapper::mapMailToDTO).collect(Collectors.toList());
            model.addAttribute("mails", mails);
            mails.forEach(System.out::println);
        }

        List<PatientDTO> patients = doctor.getPatientList()
                .stream()
                .map(patientMapper::mapToPatientDTO)
                .collect(Collectors.toList());
        model.addAttribute("patients", patients);

        Container container = new Container();
        model.addAttribute("container", container);

        return "doctor/cabinet";
    }

    @PostMapping("/patient")
    public String getPatient(Model model, @ModelAttribute("container") Container c){
        Patient patient = patientService.getById(c.getPatientId());

        model.addAttribute("patient", patientMapper.mapToPatientDTO(patient));
        List<TaskDTO> exercises = taskService.getTasksList().stream().map(taskMapper::mapTaskToDTO).collect(Collectors.toList());

        model.addAttribute("exercises", exercises);
        System.out.println(exercises);

        AssignmentDTO assignmentDTO = new AssignmentDTO();
        assignmentDTO.setPatientId(patient.getId());
        model.addAttribute("assignment", assignmentDTO);

        return "doctor/patient";
    }

    @GetMapping("/cabinet/workshop")
    public String createTask(@ModelAttribute("form") TaskDTO form) {
        return "doctor/cabinet/workshop";
    }

    @PostMapping("/cabinet/workshop")
    public String createTaskDone(@ModelAttribute("form") TaskDTO form) {
        taskService.saveTask(form);
        return "redirect:/doctor/cabinet";
    }

    @PostMapping("/patient/assign")
    public String assignExercise(@ModelAttribute("assignment")AssignmentDTO container) {
        System.out.println(container);

        prescriptionService.save(container);

        return "redirect:/doctor/cabinet";
    }

    @GetMapping("/feedback/{id}")
    public String feedbackPage(@PathVariable("id")Long id, Model model) {

        Feedback feedback = feedbackService.getById(id);

        AssignmentDTO form = assignmentMapper.mapFeedbackToAssignmentDTO(feedback);
        model.addAttribute("form", form);

        AssignmentDTO assigned = assignmentMapper.mapPrescriptionToForm(prescriptionService.getById(feedback.getPrescription().getId()));
        model.addAttribute("assigned", assigned);

        PatientDTO patientDTO = patientMapper.mapToPatientDTO(patientService.getById(form.getPatientId()));
        model.addAttribute("patient", patientDTO);

//        Container feedbackId = new Container();
//        feedbackId.setTaskId(id);
//        model.addAttribute("feedbackId", feedbackId);
        model.addAttribute("feedId", feedback.getId());

        return "doctor/feedback";
    }

    @PostMapping("/feedback/{id}")
    public String sendFeedback(@PathVariable("id") Long id, @RequestParam("grade") Short grade){
        System.out.println("Grade for patient: " + grade);

        Feedback feedback = feedbackService.getById(id);
        gradeService.addGradeToDB(feedback, grade);

        return "redirect:/doctor/cabinet";
    }

}
