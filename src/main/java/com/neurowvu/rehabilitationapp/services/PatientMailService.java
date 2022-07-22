package com.neurowvu.rehabilitationapp.services;

import com.neurowvu.rehabilitationapp.entity.PatientMail;
import com.neurowvu.rehabilitationapp.entity.Prescription;
import com.neurowvu.rehabilitationapp.repositories.PatientMailsRepository;
import com.neurowvu.rehabilitationapp.repositories.PatientsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PatientMailService {
    private final PatientMailsRepository patientMailsRepository;
    private final PatientsRepository patientsRepository;

    @Autowired
    public PatientMailService(PatientMailsRepository patientMailsRepository, PatientsRepository patientsRepository) {
        this.patientMailsRepository = patientMailsRepository;
        this.patientsRepository = patientsRepository;
    }

    public Boolean isThereAMessage(Long patientId) {
        System.out.println("IN SERVICE, patient id: " + patientId);
//        Patient patient = patientsRepository.findById(patientId).get();
//        PatientMail patientMail = patientMailsRepository.findByPatient(patient).orElse(null);

        List<PatientMail> patientMail = patientMailsRepository.findAllByPatient_Id(patientId).orElse(null);
        //System.out.println(patientMail.size());
        return patientMail.size() > 0;
    }

    public List<Prescription> getPrescriptionsByThePatientId(Long patientId) {
//        Patient patient = patientsRepository.findById(patientId).get();
        List<PatientMail> patientMail = patientMailsRepository.findAllByPatient_Id(patientId).orElse(null);
        List<Prescription> prescriptions;
        if (patientMail != null) {
            prescriptions = patientMail
                    .stream()
                    .map(PatientMail::getPrescription)
                    .toList();
            return prescriptions;
        } else {
            return null;
        }

    }
}
