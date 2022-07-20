package com.neurowvu.rehabilitationapp.services;


import com.neurowvu.rehabilitationapp.repositories.DoctorMailsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DoctorMailService {
    private final DoctorMailsRepository doctorMailsRepository;

    @Autowired
    public DoctorMailService(DoctorMailsRepository doctorMailsRepository) {
        this.doctorMailsRepository = doctorMailsRepository;
    }

    public boolean isThereFeedback(Long doctorId) {
        return false;//todo findByDoctorId
    }
}
