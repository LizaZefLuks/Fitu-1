package com.linoge.models.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Set;

/**
 * Created by Timo on 03.03.2017.
 */
@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "admission_order")
public class AdmissionOrder {

    @Id
    @GeneratedValue
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "date_of_begin")
    private String beginDate;

    @Column(name = "date_of_end")
    private String endDate;

    @OneToMany(mappedBy = "admissionOrder")
    private Set<Student> students;
}
