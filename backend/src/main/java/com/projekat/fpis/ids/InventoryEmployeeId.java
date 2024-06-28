/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.projekat.fpis.ids;

import com.projekat.fpis.domain.Employee;
import com.projekat.fpis.domain.Inventory;
import jakarta.persistence.Embeddable;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 *
 * @author Lav
 */
@Embeddable
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class InventoryEmployeeId implements Serializable{
    
    @ManyToOne
    @JoinColumn(name = "inventory_id")
    private Inventory inventory;
    
    @ManyToOne
    @JoinColumn(name = "employee_id")
    private Employee employee;
    
}
