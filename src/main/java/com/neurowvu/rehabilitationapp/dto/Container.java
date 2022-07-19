package com.neurowvu.rehabilitationapp.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
public class Container {
    private Long containerId;

    public Container(Long containerId) {
        this.containerId = containerId;
    }

    public Container() {
    }
}
