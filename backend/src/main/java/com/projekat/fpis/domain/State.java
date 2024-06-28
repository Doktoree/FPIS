/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.projekat.fpis.domain;

import com.projekat.fpis.ids.StateId;
import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
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
public class State {
    
    
    @EmbeddedId
    @Column(name = "state_id")
    private StateId stateId;

//    @ManyToOne
//    @MapsId("countryId")
//    @JoinColumn(name = "country_id", referencedColumnName = "country_id")
//    private Country country;

    @Column(nullable = false)
    private String name;
    
}
