package com.neurowvu.rehabilitationapp.repositories;

import com.neurowvu.rehabilitationapp.entity.Patient;
import com.neurowvu.rehabilitationapp.entity.PatientMail;
import com.neurowvu.rehabilitationapp.entity.Prescription;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PatientMailsRepository  extends JpaRepository<PatientMail, Long> {
    Optional<PatientMail> findByPatient(Patient patient);

}
