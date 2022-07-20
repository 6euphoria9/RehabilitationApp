package com.neurowvu.rehabilitationapp.services;

import com.neurowvu.rehabilitationapp.dto.AssignmentDTO;
import com.neurowvu.rehabilitationapp.entity.*;
import com.neurowvu.rehabilitationapp.mapper.AssignmentMapper;
import com.neurowvu.rehabilitationapp.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class PrescriptionService {
    private final PrescriptionsRepository prescriptionsRepository;
    private final MetricsRepository metricsRepository;
    private final TasksRepository tasksRepository;
    private final PatientsRepository patientsRepository;
    private final AssignmentMapper assignmentMapper;
    private final PatientMailsRepository patientMailsRepository;

    @Autowired
    public PrescriptionService(PrescriptionsRepository prescriptionsRepository, MetricsRepository metricsRepository, TasksRepository tasksRepository, PatientsRepository patientsRepository, AssignmentMapper assignmentMapper, PatientMailsRepository patientMailsRepository) {
        this.prescriptionsRepository = prescriptionsRepository;
        this.metricsRepository = metricsRepository;
        this.tasksRepository = tasksRepository;
        this.patientsRepository = patientsRepository;
        this.assignmentMapper = assignmentMapper;
        this.patientMailsRepository = patientMailsRepository;
    }

    public Prescription getById(Long prescriptionId) {
        return prescriptionsRepository.findById(prescriptionId).get();//todo need to check
    }

    public void save(AssignmentDTO dto) {
        Task task = tasksRepository.findById(dto.getTaskId()).get();

        Metric metric = assignmentMapper.mapFormToMetric(dto);
        metric.setTask(task);
        metricsRepository.save(metric);

        Patient patient = patientsRepository.findById(dto.getPatientId()).get();

        Prescription prescription = assignmentMapper.mapFormToPrescription(dto);
        prescription.setMetric(metric);
        prescription.setTask(task);
        prescription.setDate(LocalDateTime.now());
        prescription.setPatient(patient);

        prescriptionsRepository.save(prescription);

        PatientMail patientMail = new PatientMail();
        patientMail.setPatient(patient);
        patientMail.setPrescription(prescription);

        patientMailsRepository.save(patientMail);


    }
}
