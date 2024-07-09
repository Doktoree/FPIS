/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.projekat.fpis.service;

import com.projekat.fpis.domain.InventoryItem;
import com.projekat.fpis.domain.Product;
import com.projekat.fpis.dto.InventoryItemDto;
import com.projekat.fpis.dto.ProductDto;
import com.projekat.fpis.mapper.ProductMapper;
import com.projekat.fpis.repository.ProductRepository;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

/**
 *
 * @author Lav
 */
@Service
public class ProductService {
    
    
    @Autowired
    private ProductRepository productRepository;
    
    
    public List<ProductDto> getAllProducts(){
        
        List<Product> products = productRepository.findAll();
        List<ProductDto> productDtos = products.stream().map(ProductMapper::mapToProductDto).collect(Collectors.toList());

        return productDtos;
    }
    
    
    public ProductDto getProductById(Long id){
        
        Optional<Product> optionalProduct = productRepository.findById(id);
        
        if(!optionalProduct.isPresent())
            return null;
        
        Product product = optionalProduct.get();
        
        return ProductMapper.mapToProductDto(product);
    }
    
    public List<ProductDto> getProductByName(ProductDto productDto){
        
        Product product = ProductMapper.mapToProduct(productDto);
        
        List<Product> products = productRepository.findByName(product.getName());
        
        List<ProductDto> productDtos =  products.stream().map(ProductMapper::mapToProductDto).collect(Collectors.toList());
        
        return productDtos;
    }
    
    public ProductDto createProduct(ProductDto productDto){
        
        Product product = ProductMapper.mapToProduct(productDto);
        
        Product createdProduct = productRepository.save(product);
        
        return ProductMapper.mapToProductDto(createdProduct);
        
    }
    
    
    public ProductDto saveProduct(ProductDto productDto, Long id){
        
        Optional<Product> product = productRepository.findById(id);
        
        if(product.isPresent()){
            
            Product updatedProduct = product.get();
            updatedProduct.setName(productDto.getName());
            updatedProduct.setDescription(productDto.getDescription());
            updatedProduct.setPrice(productDto.getPrice());
            
            Product savedProduct = productRepository.save(updatedProduct);
            return ProductMapper.mapToProductDto(savedProduct);
            
            
        }
        
        return null;
    }
    
    public boolean deleteProduct(Long id){
        
        Optional<Product> optionalProduct = productRepository.findById(id);
        
        if(!optionalProduct.isPresent())
            return false;
        
        productRepository.deleteById(id);
        
        return true;
        
    }
    
    public boolean checkProduct(List<InventoryItemDto> inventoryItems){
        
        for(InventoryItemDto it: inventoryItems){
            
            if(getProductById(it.getProductDto().getProductId()) == null)
                return false;
            
        }
        
        return true;
        
    }
    
}
