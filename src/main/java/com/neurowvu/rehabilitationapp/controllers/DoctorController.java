package com.neurowvu.rehabilitationapp.controllers;

import com.neurowvu.rehabilitationapp.dto.Container;
import com.neurowvu.rehabilitationapp.dto.PatientDTO;
import com.neurowvu.rehabilitationapp.dto.TaskDTO;
import com.neurowvu.rehabilitationapp.entity.Doctor;
import com.neurowvu.rehabilitationapp.entity.Patient;
import com.neurowvu.rehabilitationapp.entity.User;
import com.neurowvu.rehabilitationapp.mapper.DoctorMapper;
import com.neurowvu.rehabilitationapp.mapper.PatientMapper;
import com.neurowvu.rehabilitationapp.mapper.TaskMapper;
import com.neurowvu.rehabilitationapp.security.SecurityUser;
import com.neurowvu.rehabilitationapp.services.DoctorService;
import com.neurowvu.rehabilitationapp.services.PatientService;
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
    private final TaskMapper taskMapper;
    private final TaskService taskService;

    @Autowired
    public DoctorController(DoctorService doctorService, PatientService patientService, PatientMapper patientMapper, DoctorMapper doctorMapper, TaskMapper taskMapper, TaskService taskService) {
        this.doctorService = doctorService;
        this.patientService = patientService;
        this.patientMapper = patientMapper;
        this.doctorMapper = doctorMapper;
        this.taskMapper = taskMapper;
        this.taskService = taskService;
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

        Container container = new Container();
        model.addAttribute("container", container);

        return "doctor/cabinet";
    }

    @PostMapping("/patient")
    public String getPatient(Model model, @ModelAttribute("container") Container c){
        Patient patient = patientService.getById(c.getContainerId());
        model.addAttribute("patient", patientMapper.mapToPatientDTO(patient));
        //test list with exercises
        List<TaskDTO> exercises = taskService.getTasksList().stream().map(taskMapper::mapTaskToDTO).collect(Collectors.toList());
        model.addAttribute("exercises", exercises);
        System.out.println(exercises);

        return "doctor/patient";
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
