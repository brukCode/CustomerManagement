package com.bk.Customer.Management.model;

//import com.bk.CustomerManagement.model.Customer;

public class LombokTest {
    public static void main(String[] args) {
        // Create a new Customer object using Lombok-generated constructor
        Customer customer = new Customer();
        
        // Set values using Lombok-generated setters
        customer.setFirstName("John");
        customer.setLastName("Doe");
        customer.setEmail("john.doe@example.com");
        customer.setPhone("123-456-7890");

        // Print values using Lombok-generated getters
        System.out.println("Customer Details:");
        System.out.println("First Name: " + customer.getFirstName());
        System.out.println("Last Name: " + customer.getLastName());
        System.out.println("Email: " + customer.getEmail());
        System.out.println("Phone: " + customer.getPhone());
    }
}
