package com.neurowvu.rehabilitationapp.dto;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class PatientMailDTO {
    private Long id;
    private Long patientId;
    private Long prescriptionId;
}
