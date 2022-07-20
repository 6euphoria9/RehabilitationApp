package com.neurowvu.rehabilitationapp.repositories;

import com.neurowvu.rehabilitationapp.entity.PatientMail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PatientMailsRepository  extends JpaRepository<PatientMail, Long> {
}
