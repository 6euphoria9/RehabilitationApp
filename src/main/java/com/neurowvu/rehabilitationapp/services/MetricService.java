package com.neurowvu.rehabilitationapp.services;

import com.neurowvu.rehabilitationapp.repositories.MetricsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MetricService {
    private final MetricsRepository metricsRepository;

    @Autowired
    public MetricService(MetricsRepository metricsRepository) {
        this.metricsRepository = metricsRepository;
    }
}
