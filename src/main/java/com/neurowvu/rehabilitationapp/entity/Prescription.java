package com.neurowvu.rehabilitationapp.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
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

    @Column(name = "task_id")
    @OneToMany(targetEntity = Task.class)
    private List<Task> taskList;

    @Column(name = "metric_id")
    @OneToMany(targetEntity = Metric.class)
    private List<Metric> metricList;

    @Column
    private String description;

}
