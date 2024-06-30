/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.projekat.fpis.mapper;

import com.projekat.fpis.domain.Inventory;
import com.projekat.fpis.domain.InventoryItem;
import com.projekat.fpis.dto.EmployeeDto;
import com.projekat.fpis.dto.InventoryDto;
import com.projekat.fpis.dto.InventoryItemDto;
import java.time.LocalDateTime;
import java.util.List;

/**
 *
 * @author Lav
 */
public class InventoryMapper {
    
    public static Inventory mapToInventory(InventoryDto inventoryDto){
        
        
        return new Inventory(inventoryDto.getInventoryId(), inventoryDto.getName(), inventoryDto.getDate());
        
    }
    
    
    public static InventoryDto mapToInventoryDto(Inventory inventory, List<InventoryItemDto> inventoryItems, List<EmployeeDto> employeeDtos){
        
        
        return new InventoryDto(inventory.getInventoryId(), inventory.getName(), inventory.getDate(), employeeDtos, inventoryItems);
        
    }
    
}
