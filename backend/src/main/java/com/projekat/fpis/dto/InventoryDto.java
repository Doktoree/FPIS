/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.projekat.fpis.dto;

import java.time.LocalDateTime;
import java.util.List;
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
public class InventoryDto {
    
    private Long inventoryId;

    private String name;
    
    private LocalDateTime date;
    
    private List<InventoryItemDto> inventoryItems;
    
}
