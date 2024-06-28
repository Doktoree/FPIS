/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.projekat.fpis.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinColumns;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 *
 * @author Lav
 */
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "employee_id")
    private Long employeeId;

    @Column(name = "first_name", nullable = false)
    private String firstName;

    @Column(name = "last_name", nullable = false)
    private String lastName;

    @Column(name = "jmbg", nullable = false)
    private String JMBG;

    @ManyToOne()
    @JoinColumn(name = "department_id", nullable = false)
    private Department department;

    @ManyToOne()
    @JoinColumns({
        @JoinColumn(name = "street_number_id", referencedColumnName = "street_number_id"),
        @JoinColumn(name = "street_id", referencedColumnName = "street_id"),
        @JoinColumn(name = "state_id", referencedColumnName = "state_id"),
        @JoinColumn(name = "country_id", referencedColumnName = "country_id")

    })
    private StreetNumber streetNumber;

}
