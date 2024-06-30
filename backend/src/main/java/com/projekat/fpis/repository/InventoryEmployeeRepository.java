/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.projekat.fpis.repository;

import com.projekat.fpis.domain.InventoryEmployee;
import com.projekat.fpis.ids.InventoryEmployeeId;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Lav
 */
@Repository
public interface InventoryEmployeeRepository extends JpaRepository<InventoryEmployee, InventoryEmployeeId> {
    
     List<InventoryEmployee> findById_InventoryInventoryId(Long inventoryId);
    
}
