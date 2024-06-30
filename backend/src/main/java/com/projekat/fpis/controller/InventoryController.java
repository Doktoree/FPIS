/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.projekat.fpis.controller;

import com.projekat.fpis.dto.InventoryDto;
import com.projekat.fpis.service.InventoryService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Lav
 */
@RestController
@RequestMapping("/api/inventory")
public class InventoryController {
    
    @Autowired
    private InventoryService inventoryService;
    
    
    @GetMapping
    public ResponseEntity<?> getAllInventories(){
        
        List<InventoryDto> inventoryDtos = inventoryService.getAllInventories();
        
        if(inventoryDtos == null)
            return ResponseEntity.badRequest().body("There are no inventories");
        
        return ResponseEntity.ok(inventoryDtos);
        
    }
    
    @GetMapping("{id}")
    public ResponseEntity<?> getInventoryById(@PathVariable Long id){
        
        InventoryDto inventoryDto = inventoryService.getInventoryById(id);
        
        if(inventoryDto == null)
            return ResponseEntity.badRequest().body("There is no inventory with the given id!");
        
        return ResponseEntity.ok(inventoryDto);
        
    }
    
    
    @PostMapping
    public ResponseEntity<InventoryDto> createInventory(@RequestBody InventoryDto inventoryDto){
        
        InventoryDto result = inventoryService.createInventory(inventoryDto);
        
        return ResponseEntity.status(HttpStatus.CREATED).body(result);
        
    }
    
    
    @PatchMapping("{id}")
    public ResponseEntity<?> saveInventory(@PathVariable Long id, @RequestBody InventoryDto dto){
        
        
        InventoryDto inventoryDto = inventoryService.saveInventory(id, dto);
        
        if(inventoryDto == null)
            return ResponseEntity.badRequest().body("There is no inventory with the given id!");
        
        return ResponseEntity.ok(inventoryDto);
        
    }
    
}
