package com.neurowvu.dto;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class PatientDTO {

    private Long id;
    private String firstName;
    private String lastName;
    private String address;
    private String city;

}
