/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.projekat.fpis.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.MapsId;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 *
 * @author Lav
 */
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class User {
    
      @Id
      private Long id;
    
      @OneToOne
      @MapsId
      private Employee employee;
    
      @Column(nullable = false)
      private String username;
      
      @Column(nullable = false)
      private String password;
      
     
    
}
