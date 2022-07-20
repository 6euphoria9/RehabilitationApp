package com.neurowvu.rehabilitationapp.repositories;

import com.neurowvu.rehabilitationapp.entity.Metric;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MetricsRepository extends JpaRepository<Metric, Long> {
}
