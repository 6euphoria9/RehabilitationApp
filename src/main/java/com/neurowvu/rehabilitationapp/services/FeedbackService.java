package com.neurowvu.rehabilitationapp.services;

import com.neurowvu.rehabilitationapp.repositories.FeedbacksRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FeedbackService {
    private final FeedbacksRepository feedbacksRepository;

    @Autowired
    public FeedbackService(FeedbacksRepository feedbacksRepository) {
        this.feedbacksRepository = feedbacksRepository;
    }
}
