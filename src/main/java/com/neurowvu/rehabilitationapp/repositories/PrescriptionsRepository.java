package com.neurowvu.rehabilitationapp.repositories;

import com.neurowvu.rehabilitationapp.entity.Prescription;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PrescriptionsRepository extends JpaRepository<Prescription, Long> {
    Optional<Prescription> findPrescriptionById (Long id);
}
