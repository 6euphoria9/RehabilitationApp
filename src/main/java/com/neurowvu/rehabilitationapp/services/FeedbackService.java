package com.neurowvu.rehabilitationapp.services;

import com.neurowvu.rehabilitationapp.entity.Feedback;
import com.neurowvu.rehabilitationapp.repositories.FeedbacksRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FeedbackService {
    private final FeedbacksRepository feedbacksRepository;

    @Autowired
    public FeedbackService(FeedbacksRepository feedbacksRepository) {
        this.feedbacksRepository = feedbacksRepository;
    }

    public Feedback getById(Long id){
        return feedbacksRepository.findById(id).orElse(null);
    }

    public List<Feedback> getAllById(Long id) {
        return feedbacksRepository.getAllByPatient_Id(id);
    }

}
