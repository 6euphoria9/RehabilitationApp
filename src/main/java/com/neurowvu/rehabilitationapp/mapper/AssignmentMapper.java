package com.neurowvu.rehabilitationapp.mapper;

import com.neurowvu.rehabilitationapp.dto.AssignmentDTO;
import com.neurowvu.rehabilitationapp.entity.Feedback;
import com.neurowvu.rehabilitationapp.entity.Metric;
import com.neurowvu.rehabilitationapp.entity.Prescription;
import com.neurowvu.rehabilitationapp.entity.Task;
import com.neurowvu.rehabilitationapp.repositories.MetricsRepository;
import org.springframework.stereotype.Component;

@Component
public class AssignmentMapper {
    private final MetricsRepository metricsRepository;

    public AssignmentMapper(MetricsRepository metricsRepository) {
        this.metricsRepository = metricsRepository;
    }

    public Task mapFormToTask(AssignmentDTO dto) {
        Task task = new Task();
        return null;
    }

    public Prescription mapFormToPrescription(AssignmentDTO dto) {
        Prescription prescription = new Prescription();
        prescription.setName(dto.getComment());
        return prescription;
    }

    public Metric mapFormToMetric(AssignmentDTO dto) {
        Metric metric = new Metric();
        metric.setWeekly(dto.getWeekly());
        metric.setDaily(dto.getDaily());
        metric.setDuration(dto.getDuration());
        metric.setReps(dto.getReps());
        metric.setSets(dto.getSets());

        return metric;
    }

    public AssignmentDTO mapPrescriptionToForm(Prescription prescription) {
        AssignmentDTO assignmentDTO = new AssignmentDTO();

        Metric metric = prescription.getMetric();

        assignmentDTO.setPatientId(prescription.getPatient().getId());
        assignmentDTO.setTaskId(prescription.getTask().getId());
        assignmentDTO.setComment(prescription.getName());
        assignmentDTO.setId(prescription.getId());
        assignmentDTO.setTaskName(prescription.getTask().getTaskDescription());
        assignmentDTO.setDaily(metric.getDaily());
        assignmentDTO.setWeekly(metric.getWeekly());
        assignmentDTO.setSets(metric.getSets());
        assignmentDTO.setReps(metric.getReps());
        assignmentDTO.setDuration(metric.getDuration());

        return assignmentDTO;
    }

    public AssignmentDTO mapFeedbackToAssignmentDTO(Feedback feedback) {
        AssignmentDTO assignmentDTO = new AssignmentDTO();

        //assignmentDTO.setId(feedback.getMetric().getPrescription().getId());todo get Prescription
        assignmentDTO.setPatientId(feedback.getPatient().getId());
        assignmentDTO.setTaskName(feedback.getTask().getTaskDescription());

        Metric metric = metricsRepository.findById(feedback.getMetric().getId()).get();

        assignmentDTO.setWeekly(metric.getWeekly());
        assignmentDTO.setDaily(metric.getDaily());
        assignmentDTO.setSets(metric.getSets());
        assignmentDTO.setReps(metric.getReps());
        assignmentDTO.setDuration(metric.getDuration());
        //assignmentDTO.setComment();todo Comment field

        return assignmentDTO;
    }

}
