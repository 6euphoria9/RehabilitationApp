package com.neurowvu.rehabilitationapp.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Table(name = "patient_mail")
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PatientMail {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "mail_id")
    private Long id;

    @ManyToOne()
    @JoinColumn(name = "patient_id", referencedColumnName = "patient_id")
    Patient patient;

    @OneToOne()
    @JoinColumn(name = "prescription_id", referencedColumnName = "prescription_id")
    Prescription prescription;

}