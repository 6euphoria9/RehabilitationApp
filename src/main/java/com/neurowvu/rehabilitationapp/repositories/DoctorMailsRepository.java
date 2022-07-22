package com.neurowvu.rehabilitationapp.repositories;

import com.neurowvu.rehabilitationapp.entity.DoctorMail;
import com.neurowvu.rehabilitationapp.entity.PatientMail;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface DoctorMailsRepository extends JpaRepository<DoctorMail, Integer> {

    Optional<List<DoctorMail>> findAllByDoctor_Id(Long id);

}
