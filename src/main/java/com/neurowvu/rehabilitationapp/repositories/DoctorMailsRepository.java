package com.neurowvu.rehabilitationapp.repositories;

import com.neurowvu.rehabilitationapp.entity.DoctorMail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DoctorMailsRepository extends JpaRepository<DoctorMail, Long> {
    //todo findByDoctorId
}
