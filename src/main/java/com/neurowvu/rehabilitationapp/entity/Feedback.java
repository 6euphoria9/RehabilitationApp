package com.neurowvu.rehabilitationapp.entity;

import jdk.jfr.Timestamp;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.time.LocalDateTime;

@Table(name = "feedback")
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain=true)
public class Feedback {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="feedback_id")
    private Long id;

    @OneToOne()
    @JoinColumn(name = "patient_id", referencedColumnName = "patient_id")
    Patient patient;

    @OneToOne()
    @JoinColumn(name = "task_id", referencedColumnName = "task_id")
    Task task;

    @OneToOne()
    @JoinColumn(name = "metric_id", referencedColumnName = "metric_id")
    Metric metric;
    
    @Column(name = "date")
    @Timestamp
    private LocalDateTime date;

    @OneToOne(mappedBy = "feedback")
    private DoctorMail doctorMail;

    @OneToOne
    @JoinColumn(name = "prescription_id", referencedColumnName = "prescription_id")
    private Prescription prescription;

}
