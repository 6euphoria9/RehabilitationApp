package com.neurowvu.controllers;

import com.neurowvu.services.DoctorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DoctorControllerImpl implements DoctorController{

    DoctorService doctorService;

    @Autowired
    public DoctorControllerImpl(DoctorService doctorService) {
        this.doctorService = doctorService;
    }
}
