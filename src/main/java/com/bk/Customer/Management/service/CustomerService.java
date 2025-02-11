package com.bk.Customer.Management.service;

import java.util.List;

import com.bk.Customer.Management.DTO.CustomerDto;
import com.bk.Customer.Management.model.Customer;

public interface CustomerService {
    CustomerDto registerCustomer(Customer customerInfo); 
    List<Customer> getAllCustomers(); 
    CustomerDto getCustomerById(String id); 
    CustomerDto updateCustomer(String id, Customer customerDetails); 
    boolean deleteCustomer(String id); 
 
        boolean deleteAllCustomers(); // ✅ Correct method name
    }

    

