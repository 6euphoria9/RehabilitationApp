package com.neurowvu.rehabilitationapp.entity;

import jdk.jfr.Timestamp;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.time.LocalDateTime;

@Table(name = "feedback")
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
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



}
