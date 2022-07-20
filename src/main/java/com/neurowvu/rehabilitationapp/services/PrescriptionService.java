package com.neurowvu.rehabilitationapp.services;

import com.neurowvu.rehabilitationapp.repositories.PrescriptionsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PrescriptionService {
    private final PrescriptionsRepository prescriptionsRepository;

    @Autowired
    public PrescriptionService(PrescriptionsRepository prescriptionsRepository) {
        this.prescriptionsRepository = prescriptionsRepository;
    }
}
