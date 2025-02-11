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
@RequestMapping("/customers") 
public class CustomerController {

    @Autowired
    private CustomerService customerService;

   
    @PostMapping("/register")
    public ResponseEntity<CustomerDto> register(@RequestBody(required = false) Customer customerInfo) {
        if (customerInfo == null) {
            return ResponseEntity.badRequest().body(
                CustomerDto.builder()
                 
                    .build()
            );
        }

        CustomerDto response = customerService.registerCustomer(customerInfo);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }
    
  
    @GetMapping("/getAll")
    public ResponseEntity<List<Customer>> getAllCustomers() {
        List<Customer> customers = customerService.getAllCustomers();
        return ResponseEntity.ok(customers);
    }

    
    @GetMapping("/get/{id}")
    public ResponseEntity<CustomerDto> getCustomerById(@PathVariable String id) {
        CustomerDto customer = customerService.getCustomerById(id);
        
        if (customer == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        
        return ResponseEntity.ok(customer);
    }

    
    @PutMapping("/update/{id}")
    public ResponseEntity<CustomerDto> updateCustomer(@PathVariable String id, @RequestBody Customer customerDetails) {
        CustomerDto updatedCustomer = customerService.updateCustomer(id, customerDetails);
        
        if (updatedCustomer == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        
        return ResponseEntity.ok(updatedCustomer);
    }


    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteCustomer(@PathVariable String id) {
        boolean deleted = customerService.deleteCustomer(id);
        
        if (!deleted) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        
        return ResponseEntity.noContent().build();
    }
    
    @DeleteMapping("/deleteAll")
    public ResponseEntity<String> deleteAllCustomers() {
        boolean deleted = customerService.deleteAllCustomers();
        
        if (deleted) {
            return ResponseEntity.ok("✅ All customers deleted successfully.");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                                 .body("❌ No customers found to delete.");
        }
    
 

    }
    
}



