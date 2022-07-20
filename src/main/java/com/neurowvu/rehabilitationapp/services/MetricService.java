package com.neurowvu.rehabilitationapp.services;

import com.neurowvu.rehabilitationapp.entity.Metric;
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

    public Metric getMetricById(Long metricId) {
        return metricsRepository.findById(metricId).get();//todo hope that it will works
    }
}
