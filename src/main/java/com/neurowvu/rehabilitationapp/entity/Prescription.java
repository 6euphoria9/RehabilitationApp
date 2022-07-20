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

    @OneToMany()
    @JoinColumn(name = "task_id", referencedColumnName = "task_id")
    private List<Task> taskList;

    //@Column(name = "metric_id")

    @OneToOne
    @JoinColumn(name = "patient_id", referencedColumnName = "patient_id")
    Patient patient;

    @OneToMany()
    @JoinColumn(name = "metric_id", referencedColumnName = "metric_id")
    private List<Metric> metricList;


}
