package com.neurowvu.rehabilitationapp.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Table(name = "prescription")
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Prescription {

    @Id
    @Column(name="prescription_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String name;

    @Column
    private LocalDateTime date;

    @ManyToOne()
    @JoinColumn(name = "task_id", referencedColumnName = "task_id")
    private Task task;

    @ManyToOne()
    @JoinColumn(name = "patient_id", referencedColumnName = "patient_id")
    Patient patient;

    @OneToOne()
    @JoinColumn(name = "metric_id", referencedColumnName = "metric_id")
    private Metric metric;


}
