package com.neurowvu.rehabilitationapp.dto;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class DoctorDTO {


    private Long id;
    private String firstName;
    private String lastName;


}
