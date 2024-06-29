/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.projekat.fpis.mapper;

import com.projekat.fpis.domain.InventoryItem;
import com.projekat.fpis.domain.Product;
import com.projekat.fpis.dto.InventoryItemDto;
import com.projekat.fpis.enums.State;
import com.projekat.fpis.enums.Unit;

/**
 *
 * @author Lav
 */
public class InventoryItemMapper {

    public static InventoryItem mapToInventoryItem(InventoryItemDto inventoryItemDto) {

        Product prod = new Product();
        prod.setProductId(inventoryItemDto.getProductId());

        return new InventoryItem(inventoryItemDto.getInventoryItemId(),
                inventoryItemDto.getInventory(),
                inventoryItemDto.getQuantity(),
                inventoryItemDto.getUnit(),
                inventoryItemDto.getState(),
                prod);

    }
    
    
    public static InventoryItemDto mapToInventoryItemDto(InventoryItem inventoryItem){
        
        
        return new InventoryItemDto(inventoryItem.getInventoryItemId(), 
                inventoryItem.getInventory(), 
                inventoryItem.getQuantity(), 
                inventoryItem.getUnit(), 
                inventoryItem.getState(), 
                inventoryItem.getProduct().getProductId());
        
    }

}
