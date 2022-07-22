package com.neurowvu.rehabilitationapp.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import javax.persistence.*;

@Table(name = "metric")
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain=true)
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
    @ManyToOne()
    @JoinColumn(name = "task_id", referencedColumnName = "task_id")
    private Task task;

    @OneToOne(mappedBy = "metric")
    private Prescription prescription;
}
