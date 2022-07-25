package com.neurowvu.rehabilitationapp.dto;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class FeedbackDTO {
    private String taskName;
    private Long expectedWeekly;
    private Long completedWeekly;
    private Long expectedDaily;
    private Long completedDaily;
    private Long expectedReps;
    private Long completedReps;
    private Long expectedSets;
    private Long completedSets;

}
