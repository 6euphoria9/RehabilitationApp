package com.neurowvu.rehabilitationapp.services;

import com.neurowvu.rehabilitationapp.dto.AssignmentDTO;
import com.neurowvu.rehabilitationapp.entity.Metric;
import com.neurowvu.rehabilitationapp.entity.Patient;
import com.neurowvu.rehabilitationapp.entity.Prescription;
import com.neurowvu.rehabilitationapp.entity.Task;
import com.neurowvu.rehabilitationapp.mapper.AssignmentMapper;
import com.neurowvu.rehabilitationapp.repositories.MetricsRepository;
import com.neurowvu.rehabilitationapp.repositories.PatientsRepository;
import com.neurowvu.rehabilitationapp.repositories.PrescriptionsRepository;
import com.neurowvu.rehabilitationapp.repositories.TasksRepository;
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

    @Autowired
    public PrescriptionService(PrescriptionsRepository prescriptionsRepository, MetricsRepository metricsRepository, TasksRepository tasksRepository, PatientsRepository patientsRepository, AssignmentMapper assignmentMapper) {
        this.prescriptionsRepository = prescriptionsRepository;
        this.metricsRepository = metricsRepository;
        this.tasksRepository = tasksRepository;
        this.patientsRepository = patientsRepository;
        this.assignmentMapper = assignmentMapper;
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
    }
}
