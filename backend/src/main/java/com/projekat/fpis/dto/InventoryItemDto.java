/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.projekat.fpis.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.projekat.fpis.domain.Inventory;
import com.projekat.fpis.domain.Product;
import com.projekat.fpis.enums.State;
import com.projekat.fpis.enums.Unit;
import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
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
public class InventoryItemDto {

    private Long inventoryItemId;

    @JsonIgnore
    private Inventory inventory;

    private double quantity;
    
    private Unit unit;

    private State state;
    
    private Long productId;

}
