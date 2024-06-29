/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.projekat.fpis.mapper;

import com.projekat.fpis.domain.Country;
import com.projekat.fpis.domain.Employee;
import com.projekat.fpis.domain.State;
import com.projekat.fpis.domain.Street;
import com.projekat.fpis.domain.StreetNumber;
import com.projekat.fpis.dto.EmployeeDto;
import com.projekat.fpis.ids.StreetNumberId;

/**
 *
 * @author Lav
 */
public class EmployeeMapper {
    
    public static Employee mapToEmployee(EmployeeDto employeeDto){
        
        
        return new Employee(
        
                employeeDto.getEmployeeId(),
                employeeDto.getFirstName(),
                employeeDto.getLastName(),
                employeeDto.getJMBG(),
                employeeDto.getDepartment(),
                employeeDto.getStreetNumber()
        
        );
        
    }
    
    public static EmployeeDto mapToEmployeeDto(Employee employee){
        
        Country country = employee.getStreetNumber().getStreetNumberId().getStreet().getStreetId().getStateId().getStateId().getCountryId();
        State state = employee.getStreetNumber().getStreetNumberId().getStreet().getStreetId().getStateId();
        Street street = employee.getStreetNumber().getStreetNumberId().getStreet();
        return new EmployeeDto(employee.getEmployeeId(),
                employee.getFirstName(), 
                employee.getLastName(), 
                employee.getJMBG(), 
                employee.getDepartment(), 
                country, state, street, employee.getStreetNumber());
        
    }
    
}
