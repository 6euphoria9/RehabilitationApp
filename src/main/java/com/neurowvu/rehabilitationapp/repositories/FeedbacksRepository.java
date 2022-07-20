package com.neurowvu.rehabilitationapp.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FeedbacksRepository  extends JpaRepository<FeedBack, Long> {
}
