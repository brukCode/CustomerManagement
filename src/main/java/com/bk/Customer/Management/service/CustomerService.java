package com.bk.Customer.Management.service;

import java.util.List;

import com.bk.Customer.Management.DTO.CustomerDto;
import com.bk.Customer.Management.model.Customer;

public interface CustomerService {
    CustomerDto registerCustomer(Customer customerInfo); // Register a new customer
    List<Customer> getAllCustomers(); // Retrieve all customers
    CustomerDto getCustomerById(String id); // Retrieve a customer by ID
    CustomerDto updateCustomer(String id, Customer customerDetails); // Update customer details
    boolean deleteCustomer(String id); // Delete customer by ID
   // boolean deleteAllCustomers(); // ✅ New method to delete all customers
}
