package com.neurowvu.rehabilitationapp.repositories;

import com.neurowvu.rehabilitationapp.entity.Patient;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PatientRepository extends CrudRepository<Patient, Long> {
}
