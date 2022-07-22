package com.neurowvu.rehabilitationapp.services;

import com.neurowvu.rehabilitationapp.dto.AssignmentDTO;
import com.neurowvu.rehabilitationapp.entity.*;
import com.neurowvu.rehabilitationapp.repositories.*;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class DoctorMailService {
    private final DoctorMailsRepository doctorMailsRepository;
    private final DoctorsRepository doctorsRepository;
    private final PatientsRepository patientsRepository;
    private final TasksRepository tasksRepository;
    private final MetricsRepository metricsRepository;
    private final FeedbacksRepository feedbacksRepository;
    private final PrescriptionService prescriptionService;

    public DoctorMailService(DoctorMailsRepository doctorMailsRepository, DoctorsRepository doctorsRepository, PatientsRepository patientsRepository, TasksRepository tasksRepository, MetricsRepository metricsRepository, FeedbacksRepository feedbacksRepository, PrescriptionService prescriptionService) {
        this.doctorMailsRepository = doctorMailsRepository;
        this.doctorsRepository = doctorsRepository;
        this.patientsRepository = patientsRepository;
        this.tasksRepository = tasksRepository;
        this.metricsRepository = metricsRepository;
        this.feedbacksRepository = feedbacksRepository;
        this.prescriptionService = prescriptionService;
    }

    public void sendMailToDoctor(AssignmentDTO form) {
        DoctorMail doctorMail = new DoctorMail();

        Patient patient = patientsRepository.findById(form.getPatientId()).get();
        Doctor doctor = patient.getDoctor();
        doctorMail.setDoctor(doctor);

        Feedback feedback = new Feedback();
        Task task = tasksRepository.findById(form.getTaskId()).get();
        Metric metric = new Metric();
        metric.setTask(task);
        metric.setWeekly(form.getWeekly());
        metric.setDaily(form.getDaily());
        metric.setSets(form.getSets());
        metric.setReps(form.getReps());
        metric.setDuration(form.getDuration());
        metricsRepository.save(metric);

        Prescription prescription = prescriptionService.getById(form.getId());

        feedback.setPrescription(prescription);
        feedback.setPatient(patient);
        feedback.setTask(task);
        feedback.setDate(LocalDateTime.now());
        feedback.setMetric(metric);
        feedbacksRepository.save(feedback);

        doctorMail.setFeedback(feedback);

        doctorMailsRepository.save(doctorMail);
    }

    public Boolean isThereAMessage(Long doctorId) {
        System.out.println("IN SERVICE, doctor id: " + doctorId);
//        Patient patient = patientsRepository.findById(patientId).get();
//        PatientMail patientMail = patientMailsRepository.findByPatient(patient).orElse(null);

        List<DoctorMail> doctorMails = doctorMailsRepository.findAllByDoctor_Id(doctorId).orElse(null);
        System.out.println(doctorMails.size());
        return doctorMails.size() > 0;
    }

    public List<DoctorMail> getAllMessagesByDoctorId(Long id){
        return doctorMailsRepository.findAllByDoctor_Id(id).orElse(null);
    }
}
