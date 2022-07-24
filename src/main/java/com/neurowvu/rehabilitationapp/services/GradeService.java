package com.neurowvu.rehabilitationapp.services;

import com.neurowvu.rehabilitationapp.entity.Feedback;
import com.neurowvu.rehabilitationapp.entity.Grade;
import com.neurowvu.rehabilitationapp.entity.Patient;
import com.neurowvu.rehabilitationapp.entity.Prescription;
import com.neurowvu.rehabilitationapp.repositories.DoctorMailsRepository;
import com.neurowvu.rehabilitationapp.repositories.GradesRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class GradeService {
    private final GradesRepository gradesRepository;
    private final DoctorMailsRepository doctorMailsRepository;

    public GradeService(GradesRepository gradesRepository, DoctorMailsRepository doctorMailsRepository) {
        this.gradesRepository = gradesRepository;
        this.doctorMailsRepository = doctorMailsRepository;
    }

    @Transactional
    public void addGradeToDB(Feedback feedback, short grade){
        Grade gradeDB = new Grade();

        Prescription prescription = feedback.getPrescription();
        Patient patient = feedback.getPatient();

        gradeDB.setPatient(patient);
        gradeDB.setPrescription(prescription);
        gradeDB.setGrade(grade);

        doctorMailsRepository.deleteDoctorMailByFeedback_id(feedback.getId());

        gradesRepository.save(gradeDB);
    }

    public Grade getByPrescriptionId(Long id) {
        return gradesRepository.getByPrescriptionId(id);
    }
}
