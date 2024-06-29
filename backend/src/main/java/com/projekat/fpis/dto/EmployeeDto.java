/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.projekat.fpis.dto;

import com.projekat.fpis.domain.Country;
import com.projekat.fpis.domain.Department;
import com.projekat.fpis.domain.State;
import com.projekat.fpis.domain.Street;
import com.projekat.fpis.domain.StreetNumber;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 *
 * @author Lav
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeDto {
    
    private Long employeeId;

    private String firstName;

    private String lastName;

    private String JMBG;

    private Department department;

    private Country country;
    
    private State state;
    
    private Street street;
    
    private StreetNumber streetNumber;
    
}
