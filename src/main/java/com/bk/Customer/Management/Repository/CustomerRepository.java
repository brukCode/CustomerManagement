package com.bk.Customer.Management.Repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.bk.Customer.Management.model.Customer;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {

    // ✅ Use correct method name with correct type:
    Optional<Customer> findById(Long id);

    // ✅ Correct delete method:
    void deleteById(Long id);
}
