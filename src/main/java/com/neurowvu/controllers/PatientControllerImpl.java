package com.neurowvu.controllers;

import com.neurowvu.services.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PatientControllerImpl implements PatientController{

    PatientService patientService;

    @Autowired
    public PatientControllerImpl(PatientService patientService) {
        this.patientService = patientService;
    }
}
