/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.projekat.fpis.repository;

import com.projekat.fpis.domain.InventoryItem;
import com.projekat.fpis.ids.InventoryItemId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Lav
 */
@Repository
public interface InventoryItemRepository extends JpaRepository<InventoryItem, InventoryItemId> {
    
}
