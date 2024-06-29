/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.projekat.fpis.mapper;

import com.projekat.fpis.domain.Product;
import com.projekat.fpis.dto.ProductDto;

/**
 *
 * @author Lav
 */
public class ProductMapper {
    
    
    public static Product mapToProduct(ProductDto productDto){
        
        return new Product(productDto.getProductId(), 
                productDto.getName(), 
                productDto.getPrice(), 
                productDto.getDescription());
        
    }
    
    
    public static ProductDto mapToProductDto(Product product){
        
        return new ProductDto(product.getProductId(),
                product.getName(), 
                product.getPrice(), 
                product.getDescription());
        
    }
    
}
