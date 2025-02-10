package com.bk.Customer.Management.service;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.DeleteMapping;

import com.bk.Customer.Management.DTO.CustomerDto;
import com.bk.Customer.Management.Repository.CustomerRepository;
import com.bk.Customer.Management.model.Customer;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;

    @Autowired
    public CustomerServiceImpl(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    /**
     * Register a new customer
     */
    @Override
    public CustomerDto registerCustomer(Customer customerInfo) {
        log.info("🔍 Registering customer: {}", customerInfo.getEmail());
        
        Customer savedCustomer = customerRepository.save(customerInfo);

        log.info("✅ Customer registered successfully: {}", savedCustomer.getId());
        
        return new CustomerDto(savedCustomer);
    }

    /**
     * Retrieve all customers
     */
    @Override
    public List<Customer> getAllCustomers() {
        log.info("📋 Fetching all customers...");
        return customerRepository.findAll();
    }

    /**
     * Retrieve a customer by ID
     */
    @Override
    public CustomerDto getCustomerById(String id) {
        log.info("🔍 Fetching customer with ID: {}", id);

        // ✅ Convert String to Long
        Long customerId = Long.parseLong(id);

        Optional<Customer> customer = customerRepository.findById(customerId);

        if (customer.isEmpty()) {
            log.warn("❌ Customer not found with ID: {}", id);
            return null;
        }

        log.info("✅ Customer found: {}", id);
        return new CustomerDto(customer.get());
    }

    /**
     * Update an existing customer
     */
    @Override
    public CustomerDto updateCustomer(String id, Customer customerDetails) {
        log.info("🔄 Updating customer with ID: {}", id);

        // ✅ Convert String to Long
        Long customerId = Long.parseLong(id);

        Optional<Customer> existingCustomer = customerRepository.findById(customerId);

        if (existingCustomer.isEmpty()) {
            log.warn("❌ Customer not found with ID: {}", id);
            return null;
        }

        Customer customerToUpdate = existingCustomer.get();
        customerToUpdate.setFirstName(customerDetails.getFirstName());
        customerToUpdate.setLastName(customerDetails.getLastName());
        customerToUpdate.setEmail(customerDetails.getEmail());
        customerToUpdate.setPhone(customerDetails.getPhone());

        Customer updatedCustomer = customerRepository.save(customerToUpdate);
        log.info("✅ Customer updated successfully: {}", id);

        return new CustomerDto(updatedCustomer);
    }

    /**
     * Delete a customer by ID
     */
    @Override
    public boolean deleteCustomer(String id) {
        log.info("🗑 Deleting customer with ID: {}", id);

        // ✅ Convert String to Long
        Long customerId = Long.parseLong(id);

        Optional<Customer> customer = customerRepository.findById(customerId);
        if (customer.isEmpty()) {
            log.warn("❌ Customer not found with ID: {}", id);
            return false;
        }

        // ✅ Correct delete method
        customerRepository.deleteById(customerId);
        log.info("✅ Customer deleted successfully: {}", id);
        
        return true;
    }
    
//    @DeleteMapping("/deleteAll")
//    public ResponseEntity<String> deleteAllCustomers() {
//        boolean deleted = customerRepository.de
//        if (deleted) {
//            return ResponseEntity.ok("✅ All customers deleted successfully.");
//        } else {
//            return ResponseEntity.status(HttpStatus.NOT_FOUND)
//                                 .body("❌ No customers found to delete.");
//        }
//    }

}
