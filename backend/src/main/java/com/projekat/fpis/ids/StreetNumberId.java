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
import jakarta.persistence.PrimaryKeyJoinColumn;
import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 *
 * @author Lav
 */
@Embeddable
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class StreetNumberId implements Serializable{

    @Column(name = "street_number_id")
    private Long streetNumberId;

    @ManyToOne
    @JoinColumns({
    
    @JoinColumn(name = "street_id", referencedColumnName = "street_id"),
    @JoinColumn(name = "state_id", referencedColumnName = "state_id"),
    @JoinColumn(name = "country_id", referencedColumnName = "country_id")
    
       
    })
    private Street street;
}
