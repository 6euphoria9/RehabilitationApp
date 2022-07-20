package com.neurowvu.rehabilitationapp.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Table(name = "doctor_mail")
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class DoctorMail {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "mail_id")
    private Long id;

    @ManyToOne()
    @JoinColumn(name = "doctor_id", referencedColumnName = "doctor_id")
    Doctor doctor;

    @OneToOne()
    @JoinColumn(name = "feedback_id", referencedColumnName = "feedback_id")
    Feedback feedback;

}
