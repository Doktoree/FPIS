/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.projekat.fpis.ids;

import com.projekat.fpis.domain.Country;
import com.projekat.fpis.domain.State;
import com.projekat.fpis.domain.Street;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinColumns;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
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
@Embeddable
public class StreetId implements Serializable{

    @Column(name = "street_id")
    private Long streetId;

    @ManyToOne
    @JoinColumns({
    
    @JoinColumn(name = "state_id", referencedColumnName = "state_id"),
    @JoinColumn(name = "country_id", referencedColumnName = "country_id")
    })
    private State stateId;
    
    

}
