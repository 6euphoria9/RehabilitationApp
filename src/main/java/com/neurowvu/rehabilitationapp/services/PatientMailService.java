package com.neurowvu.rehabilitationapp.services;

import com.neurowvu.rehabilitationapp.repositories.PatientMailsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PatientMailService {
    private final PatientMailsRepository patientMailsRepository;

    @Autowired
    public PatientMailService(PatientMailsRepository patientMailsRepository) {
        this.patientMailsRepository = patientMailsRepository;
    }
}
