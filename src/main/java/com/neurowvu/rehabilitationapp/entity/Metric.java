package com.neurowvu.rehabilitationapp.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Table(name = "metric")
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Metric {

    @Id
    @Column(name = "metric_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private Long weekly;

    @Column
    private Long daily;

    @Column
    private Long sets;

    @Column
    private Long reps;

    @Column
    private Long duration;

    //@Column(name = "task_id")
    @OneToOne()
    @JoinColumn(name = "task_id", referencedColumnName = "task_id")
    private Task task;
}
