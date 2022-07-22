package com.neurowvu.rehabilitationapp.repositories;

import com.neurowvu.rehabilitationapp.entity.Patient;
import com.neurowvu.rehabilitationapp.entity.PatientMail;
import com.neurowvu.rehabilitationapp.entity.Prescription;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PatientMailsRepository  extends JpaRepository<PatientMail, Long> {
    Optional<List<PatientMail>> findAllByPatient_Id(Long id);

    void removeByPrescriptionId(Long id);
    List<PatientMail> findAllByPatient(Patient patient);

}
