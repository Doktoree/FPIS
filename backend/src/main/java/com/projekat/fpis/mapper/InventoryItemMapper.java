/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.projekat.fpis.mapper;

import com.projekat.fpis.domain.InventoryItem;
import com.projekat.fpis.domain.Product;
import com.projekat.fpis.dto.InventoryItemDto;
import com.projekat.fpis.dto.ProductDto;
import com.projekat.fpis.enums.State;
import com.projekat.fpis.enums.Unit;

/**
 *
 * @author Lav
 */
public class InventoryItemMapper {

    public static InventoryItem mapToInventoryItem(InventoryItemDto inventoryItemDto) {

        Product product = ProductMapper.mapToProduct(inventoryItemDto.getProductDto());

        return new InventoryItem(inventoryItemDto.getInventoryItemId(),
                inventoryItemDto.getInventory(),
                inventoryItemDto.getQuantity(),
                inventoryItemDto.getUnit(),
                inventoryItemDto.getState(),
                product);

    }
    
    
    public static InventoryItemDto mapToInventoryItemDto(InventoryItem inventoryItem){
        
        ProductDto productDto = ProductMapper.mapToProductDto(inventoryItem.getProduct());
        
        return new InventoryItemDto(inventoryItem.getInventoryItemId(), 
                inventoryItem.getInventory(), 
                inventoryItem.getQuantity(), 
                inventoryItem.getUnit(), 
                inventoryItem.getState(), 
                productDto);
        
    }

}
