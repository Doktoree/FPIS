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
import com.projekat.fpis.dto.ProductDto;
import com.projekat.fpis.ids.InventoryEmployeeId;
import com.projekat.fpis.ids.InventoryItemId;
import com.projekat.fpis.mapper.EmployeeMapper;
import com.projekat.fpis.mapper.InventoryItemMapper;
import com.projekat.fpis.mapper.InventoryMapper;
import com.projekat.fpis.mapper.ProductMapper;
import com.projekat.fpis.repository.EmployeeRepository;
import com.projekat.fpis.repository.InventoryEmployeeRepository;
import com.projekat.fpis.repository.InventoryItemRepository;
import com.projekat.fpis.repository.InventoryRepository;
import com.projekat.fpis.repository.ProductRepository;
import java.time.Duration;
import java.time.LocalDateTime;
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

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ProductService productService;

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

            for (InventoryItem it : inventoryItems) {
                Product product = null;
                Optional<Product> optionalProduct = productRepository.findById(it.getProduct().getProductId());
                if (optionalProduct.isPresent()) {
                    product = optionalProduct.get();
                    it.setProduct(product);
                }

            }
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

        for (InventoryItem it : inventoryItems) {
            Product product = ProductMapper.mapToProduct(productService.getProductById(it.getProduct().getProductId()));
            it.setProduct(product);

        }
        List<InventoryItemDto> inventoryDtos = inventoryItems.stream().map(InventoryItemMapper::mapToInventoryItemDto).collect(Collectors.toList());

        List<InventoryEmployee> inventoryEmployees = inventoryEmployeeRepository.findById_InventoryInventoryId(inventory.getInventoryId());
        List<EmployeeDto> employees = new ArrayList<>();

        inventoryEmployees.stream().forEach(ie -> {

            Employee emp = ie.getId().getEmployee();
            Optional<Employee> optionalEmployee = employeeRepository.findById(emp.getEmployeeId());
            Employee employee = optionalEmployee.get();
            employees.add(EmployeeMapper.mapToEmployeeDto(employee));

        });

        return InventoryMapper.mapToInventoryDto(inventory, inventoryDtos, employees);

    }

    public InventoryDto createInventory(InventoryDto inventoryDto) {

        if (!productService.checkProduct(inventoryDto.getInventoryItems())) {
            return null;
        }

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
            Product product = ProductMapper.mapToProduct(productService.getProductById(item.getProduct().getProductId()));
            item.setProduct(product);
            createdInventoryItemDtos.add(InventoryItemMapper.mapToInventoryItemDto(item));

        });

        return InventoryMapper.mapToInventoryDto(createdInventory, createdInventoryItemDtos, createdEmployeesDto);
    }

    public InventoryDto saveInventory(Long id, InventoryDto inventoryDto) {

        if (!productService.checkProduct(inventoryDto.getInventoryItems())) {
            return null;
        }

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
                    Product product = ProductMapper.mapToProduct(productService.getProductById(it.getProduct().getProductId()));
                    it.setProduct(product);
                    updatedInventoryItems.add(it);

                }

            }

        }

        return InventoryMapper.mapToInventoryDto(updatedInventory, updatedInventoryItems.stream().map(InventoryItemMapper::mapToInventoryItemDto).collect(Collectors.toList()), employees);

    }

    public boolean deleteInventoryItem(Long invetoryId, Long inventoryItemId) {

        System.out.println("Usao u metodu 1!");
        Inventory inventory = new Inventory();
        inventory.setInventoryId(invetoryId);
        InventoryItemId id = new InventoryItemId(inventoryItemId, inventory);

        Optional<InventoryItem> optionalInventoryItem = inventoryItemRepository.findById(id);
        System.out.println("Usao u metodu 2!");
        if (!optionalInventoryItem.isPresent()) {
            System.out.println("Vratio false 1!");
            return false;

        }
        Duration duration = Duration.between(LocalDateTime.now(), optionalInventoryItem.get().getInventory().getDate());
        if (duration.toDays() > 1) {
            return false;
        }
        inventoryItemRepository.deleteById(id);
        System.out.println("Ende metoda 1!");
        return true;

    }
   
    
    public void updateInventory(InventoryDto inventoryDto){
        
        Optional<Inventory> optionalInventory = inventoryRepository.findById(inventoryDto.getInventoryId());
        Inventory inventory = optionalInventory.get();
        List<Employee> employees = inventoryDto.getEmployeeDtos().stream().map(EmployeeMapper::mapToEmployee).collect(Collectors.toList());
        Employee employee = employees.get(0);
        List<InventoryItem> inventoryItems = inventoryDto.getInventoryItems().stream().map(InventoryItemMapper::mapToInventoryItem).collect(Collectors.toList());
        List<InventoryEmployee> inventoryEmployees = inventoryEmployeeRepository.findById_InventoryInventoryId(inventory.getInventoryId());
        boolean flag = false;
        System.out.println("Prosao update 1");
        for(InventoryEmployee ie: inventoryEmployees){
            
            if(employee.getEmployeeId().compareTo(ie.getId().getEmployee().getEmployeeId()) == 0){
                flag = true;
                break;
            }
            
        }
        System.out.println("Prosao update 2");
        if(!flag){
            
            InventoryEmployeeId id = new InventoryEmployeeId(inventory, employee);
            inventoryEmployeeRepository.save(new InventoryEmployee(id));
        }
        System.out.println("Prosao update 3");
        inventoryItems.stream().forEach(it -> {

            it.setInventory(inventory);
            System.out.println("Inventort item update: " + it.toString());
            inventoryItemRepository.save(it);
        
        });
        System.out.println("Prosao update 4");
    }

}
