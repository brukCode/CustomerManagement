package com.bk.Customer.Management.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.bk.Customer.Management.model.Customer;
import com.bk.Customer.Management.service.CustomerService;
import com.bk.Customer.Management.DTO.CustomerDto;

@RestController
@RequestMapping("/customers") // Base path for all customer endpoints
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    /**
     * Register a new customer
     */
    @PostMapping("/register")
    public ResponseEntity<CustomerDto> register(@RequestBody(required = false) Customer customerInfo) {
        if (customerInfo == null) {
            return ResponseEntity.badRequest().body(
                CustomerDto.builder()
                   // .note("❌ Request body is missing or invalid.")
                    .build()
            );
        }

        CustomerDto response = customerService.registerCustomer(customerInfo);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }
    
    /**
     * Get all customers
     */
    @GetMapping("/getAll")
    public ResponseEntity<List<Customer>> getAllCustomers() {
        List<Customer> customers = customerService.getAllCustomers();
        return ResponseEntity.ok(customers);
    }

    /**
     * Get a customer by ID
     */
    @GetMapping("/get/{id}")
    public ResponseEntity<CustomerDto> getCustomerById(@PathVariable String id) {
        CustomerDto customer = customerService.getCustomerById(id);
        
        if (customer == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        
        return ResponseEntity.ok(customer);
    }

    /**
     * Update an existing customer
     */
    @PutMapping("/update/{id}")
    public ResponseEntity<CustomerDto> updateCustomer(@PathVariable String id, @RequestBody Customer customerDetails) {
        CustomerDto updatedCustomer = customerService.updateCustomer(id, customerDetails);
        
        if (updatedCustomer == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        
        return ResponseEntity.ok(updatedCustomer);
    }

    /**
     * Delete a customer by ID
     */
//    @DeleteMapping("/delete/{id}")
//    public ResponseEntity<Void> deleteCustomer(@PathVariable String id) {
//        boolean deleted = customerService.deleteCustomer(id);
//        
//        if (!deleted) {
//            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
//        }
//        
//        return ResponseEntity.noContent().build();
//    }
}

