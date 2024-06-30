/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.projekat.fpis.service;

import com.projekat.fpis.domain.Employee;
import com.projekat.fpis.domain.Inventory;
import com.projekat.fpis.domain.InventoryEmployee;
import com.projekat.fpis.domain.InventoryItem;
import com.projekat.fpis.domain.Product;
import com.projekat.fpis.dto.EmployeeDto;
import com.projekat.fpis.dto.InventoryDto;
import com.projekat.fpis.dto.InventoryItemDto;
import com.projekat.fpis.ids.InventoryEmployeeId;
import com.projekat.fpis.mapper.EmployeeMapper;
import com.projekat.fpis.mapper.InventoryItemMapper;
import com.projekat.fpis.mapper.InventoryMapper;
import com.projekat.fpis.repository.EmployeeRepository;
import com.projekat.fpis.repository.InventoryEmployeeRepository;
import com.projekat.fpis.repository.InventoryItemRepository;
import com.projekat.fpis.repository.InventoryRepository;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Lav
 */
@Service
public class InventoryService {

    @Autowired
    private InventoryRepository inventoryRepository;

    @Autowired
    private InventoryItemRepository inventoryItemRepository;

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private InventoryEmployeeRepository inventoryEmployeeRepository;

    public List<InventoryDto> getAllInventories() {

        List<Inventory> inventories = inventoryRepository.findAll();
        List<InventoryDto> inventoryDtos = new ArrayList<>();
        List<EmployeeDto> employees = new ArrayList<>();
        inventories.stream().forEach(i -> {

            List<InventoryEmployee> inventoryEmployees = inventoryEmployeeRepository.findById_InventoryInventoryId(i.getInventoryId());
            
            inventoryEmployees.stream().forEach(ie -> {
            
                    Employee emp = ie.getId().getEmployee();
                    Optional<Employee> optionalEmployee = employeeRepository.findById(emp.getEmployeeId());
                    Employee employee = optionalEmployee.get();
                    employees.add(EmployeeMapper.mapToEmployeeDto(employee));
            
            });
            
            List<InventoryItem> inventoryItems = inventoryItemRepository.findByInventory(i);
            List<InventoryItemDto> inventoryItemDtos = inventoryItems.stream().map(InventoryItemMapper::mapToInventoryItemDto).collect(Collectors.toList());
            InventoryDto dto = InventoryMapper.mapToInventoryDto(i, inventoryItemDtos, employees);
            inventoryDtos.add(dto);

        });

        return inventoryDtos;

    }

    public InventoryDto getInventoryById(Long id) {

        Optional<Inventory> optionalInventory = inventoryRepository.findById(id);

        if (!optionalInventory.isPresent()) {
            return null;
        }

        Inventory inventory = optionalInventory.get();

        List<InventoryItem> inventoryItems = inventoryItemRepository.findByInventory(inventory);
        List<InventoryItemDto> inventoryDtos = inventoryItems.stream().map(InventoryItemMapper::mapToInventoryItemDto).collect(Collectors.toList());
        
        List<InventoryEmployee> inventoryEmployees = inventoryEmployeeRepository.findById_InventoryInventoryId(inventory.getInventoryId());
        List<EmployeeDto> employees = new ArrayList<>();
        
        inventoryEmployees.stream().forEach(ie -> {
            
                    Employee emp = ie.getId().getEmployee();
                    Optional<Employee> optionalEmployee = employeeRepository.findById(emp.getEmployeeId());
                    Employee employee = optionalEmployee.get();
                    employees.add(EmployeeMapper.mapToEmployeeDto(employee));
            
            });
        
        return InventoryMapper.mapToInventoryDto(inventory, inventoryDtos,employees);

    }

    public InventoryDto createInventory(InventoryDto inventoryDto) {

        List<EmployeeDto> employeeDtos = inventoryDto.getEmployeeDtos();
        Inventory inventory = InventoryMapper.mapToInventory(inventoryDto);
        List<InventoryItemDto> inventoryItemDtos = inventoryDto.getInventoryItems();
        List<InventoryItem> inventoryItems = inventoryItemDtos.stream().map(InventoryItemMapper::mapToInventoryItem).collect(Collectors.toList());
        List<InventoryItemDto> createdInventoryItemDtos = new ArrayList<>();
        List<EmployeeDto> createdEmployeesDto = new ArrayList<>();
        
        Inventory createdInventory = inventoryRepository.save(inventory);
        
        employeeDtos.stream().forEach(emp -> {
        
            Optional<Employee> optionalEmployee = employeeRepository.findById(emp.getEmployeeId());
            Employee employee = optionalEmployee.get();
            
            InventoryEmployeeId id = new InventoryEmployeeId(createdInventory, employee);
            InventoryEmployee inventoryEmployee = new InventoryEmployee(id);
            inventoryEmployeeRepository.save(inventoryEmployee);
            createdEmployeesDto.add(EmployeeMapper.mapToEmployeeDto(employee));
            
        
        });
        
        inventoryItems.stream().forEach(it -> {

            it.setInventory(createdInventory);
            InventoryItem item = inventoryItemRepository.save(it);

            createdInventoryItemDtos.add(InventoryItemMapper.mapToInventoryItemDto(item));

        });

        return InventoryMapper.mapToInventoryDto(createdInventory, createdInventoryItemDtos,createdEmployeesDto);
    }

    public InventoryDto saveInventory(Long id, InventoryDto inventoryDto) {

        Optional<Inventory> optionalInventory = inventoryRepository.findById(id);
        List<InventoryItem> updatedInventoryItems = new ArrayList<>();

        if (!optionalInventory.isPresent()) {
            return null;
        }

        Inventory updatedInventory = optionalInventory.get();
        List<InventoryItem> inventoryItems = inventoryItemRepository.findByInventory(updatedInventory);

        updatedInventory.setDate(inventoryDto.getDate());
        updatedInventory.setName(inventoryDto.getName());

        List<InventoryEmployee> inventoryEmployees = inventoryEmployeeRepository.findById_InventoryInventoryId(updatedInventory.getInventoryId());
        List<EmployeeDto> employees = new ArrayList<>();
        inventoryEmployees.stream().forEach(ie -> {
            
                    Employee emp = ie.getId().getEmployee();
                    Optional<Employee> optionalEmployee = employeeRepository.findById(emp.getEmployeeId());
                    Employee employee = optionalEmployee.get();
                    employees.add(EmployeeMapper.mapToEmployeeDto(employee));
            
            });
        
        
        for (InventoryItemDto itDto : inventoryDto.getInventoryItems()) {

            for (InventoryItem it : inventoryItems) {

                if (it.getInventoryItemId().compareTo(itDto.getInventoryItemId()) == 0) {

                    it.setQuantity(itDto.getQuantity());
                    it.setUnit(itDto.getUnit());
                    it.setState(itDto.getState());
                    Product product = new Product();
                    product.setProductId(itDto.getProductId());
                    it.setProduct(product);
                    updatedInventoryItems.add(it);

                }

            }

        }

        return InventoryMapper.mapToInventoryDto(updatedInventory, updatedInventoryItems.stream().map(InventoryItemMapper::mapToInventoryItemDto).collect(Collectors.toList()), employees);

    }

}
