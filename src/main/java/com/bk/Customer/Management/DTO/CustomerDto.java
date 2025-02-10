package com.bk.Customer.Management.DTO;

import com.bk.Customer.Management.model.Customer;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CustomerDto {

    private Long customerId;
    private String firstName;
    private String lastName;
    private String email;
    private String phone;
    private String notes;
    private Object address;
    private Object customFields;

    // ✅ Constructor to accept a Customer object
    public CustomerDto(Customer customer) {
        this.customerId = customer.getId();
        this.firstName = customer.getFirstName();
        this.lastName = customer.getLastName();
        this.email = customer.getEmail();
        this.phone = customer.getPhone();
       // this.notes = customer.getNotes();
        //this.address = customer.getAddress();
       // this.customFields = customer.getCustomFields();
    }

    // ✅ Static method for conversion (alternative approach)
    public static CustomerDto fromCustomer(Customer customer) {
        return new CustomerDto(customer);
    }
}
