/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.projekat.fpis.service;

import com.projekat.fpis.domain.Department;
import com.projekat.fpis.domain.Employee;
import com.projekat.fpis.domain.User;
import com.projekat.fpis.repository.DepartmentRepository;
import com.projekat.fpis.repository.EmployeeRepository;
import com.projekat.fpis.repository.UserRepository;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Lav
 */
@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private EmployeeRepository employeeRepository;
    
    @Autowired
    private DepartmentRepository departmentRepository;
    
    public User checkUser(User user) {

        User returnedUser = userRepository.findByUsernameAndPassword(user.getUsername(), user.getPassword());
        if (returnedUser == null) {
            return null;
        }
        
        Optional<Employee> optionalEmployee = employeeRepository.findById(returnedUser.getEmployee().getEmployeeId());
        
        if(!optionalEmployee.isPresent()){
            return null;
        }
        
        Employee employee = optionalEmployee.get();
        
        Optional<Department> optionalDepartment = departmentRepository.findById(employee.getDepartment().getDepartmentId());
        
        if(!optionalDepartment.isPresent()){
            return null;
        }
        
        Department department = optionalDepartment.get();
        employee.setDepartment(department);
        
        returnedUser.setEmployee(employee);
        return returnedUser;
    }

}
