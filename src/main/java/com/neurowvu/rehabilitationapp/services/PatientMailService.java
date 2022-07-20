package com.neurowvu.rehabilitationapp.services;

import com.neurowvu.rehabilitationapp.entity.Patient;
import com.neurowvu.rehabilitationapp.entity.PatientMail;
import com.neurowvu.rehabilitationapp.repositories.PatientMailsRepository;
import com.neurowvu.rehabilitationapp.repositories.PatientsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PatientMailService {
    private final PatientMailsRepository patientMailsRepository;
    private final PatientsRepository patientsRepository;

    @Autowired
    public PatientMailService(PatientMailsRepository patientMailsRepository, PatientsRepository patientsRepository) {
        this.patientMailsRepository = patientMailsRepository;
        this.patientsRepository = patientsRepository;
    }

    public Boolean isThereAMessage (Long patientId) {
        System.out.println("IN SERVICE, patient id: " + patientId);
        Patient patient = patientsRepository.findById(patientId).get();
        PatientMail patientMail = patientMailsRepository.findByPatient(patient).orElse(null);

        //System.out.println(patientMail);
        return patientMail != null;
    }

    public Long getPrescriptionId (Long patientId) {
        Patient patient = patientsRepository.findById(patientId).get();
        PatientMail patientMail = patientMailsRepository.findByPatient(patient).orElse(null);

        return patientMail.getPrescription().getId();
    }
}
