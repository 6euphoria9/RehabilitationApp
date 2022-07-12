package com.neurowvu.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;

@Table(name = "metrics")
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Metric {

    @Id
    @Column(name = "metric_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "task_id")
    @OneToOne(targetEntity = Task.class)
    private Task task;
}
