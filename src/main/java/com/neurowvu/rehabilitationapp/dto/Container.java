package com.neurowvu.rehabilitationapp.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
public class Container {
    private Long patientId;
    private Long taskId;

    public Container(Long patientId) {
        this.patientId = patientId;
    }

    public Container() {
    }
}
