/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.projekat.fpis.controller;

import com.projekat.fpis.dto.InventoryItemDto;
import com.projekat.fpis.dto.ProductDto;
import com.projekat.fpis.service.ProductService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
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
@RequestMapping("/api/product")
@CrossOrigin(origins = "http://localhost:5173")
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping
    public ResponseEntity<?> getAllProducts() {

        List<ProductDto> products = productService.getAllProducts();

        if (products == null) {
            return ResponseEntity.badRequest().body("There are no products");
        }

        return ResponseEntity.ok(products);

    }

    @GetMapping("{id}")
    public ResponseEntity<?> getProductById(@PathVariable Long id) {

        ProductDto product = productService.getProductById(id);

        if (product == null) {
            return ResponseEntity.badRequest().body("There is no product with given ID!");
        }

        return ResponseEntity.ok(product);
    }

    @PostMapping("/search")
    public ResponseEntity<?> getProductByExample(@RequestBody ProductDto productDto) {

        List<ProductDto> productDtos = productService.getProductByExample(productDto);

        if (productDtos == null) {
            return ResponseEntity.badRequest().body("There are no products for given example!");
        }

        return ResponseEntity.ok(productDtos);

    }

    @PostMapping
    public ResponseEntity<?> createProduct(@RequestBody ProductDto productDto) {

        ProductDto createdProductDto = productService.createProduct(productDto);

        return ResponseEntity.status(HttpStatus.CREATED).body(createdProductDto);

    }

    @PatchMapping("{id}")
    public ResponseEntity<?> saveProduct(@PathVariable Long id, @RequestBody ProductDto productDto) {

        ProductDto savedProductDto = productService.saveProduct(productDto, id);

        if (savedProductDto == null) {
            return ResponseEntity.badRequest().body("There is no product with given ID!");
        }

        return ResponseEntity.ok(savedProductDto);

    }

    @DeleteMapping("{id}")
    public ResponseEntity<?> deleteProduct(@PathVariable Long id) {

        if (!productService.deleteProduct(id)) {
            return ResponseEntity.badRequest().body("There is no product with given ID!");
        }

        return ResponseEntity.ok("Product is succesfully deleted!");

    }

}
