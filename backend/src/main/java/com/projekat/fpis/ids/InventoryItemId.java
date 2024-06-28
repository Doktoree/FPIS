/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.projekat.fpis.ids;

import com.projekat.fpis.domain.Inventory;
import java.io.Serializable;
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
@AllArgsConstructor
@NoArgsConstructor
public class InventoryItemId implements Serializable{
    
    private Long inventoryItemId;
    
    private Inventory inventory;
    
    
}
