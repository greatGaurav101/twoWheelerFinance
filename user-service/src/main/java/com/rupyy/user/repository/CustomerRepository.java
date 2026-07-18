package com.rupyy.user.repository;

import com.rupyy.user.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface CustomerRepository extends JpaRepository<Customer, UUID> {

    Optional<Customer> findByMobile(String mobile);

    Optional<Customer> findByMobileAndEmail(String mobile,String email);

    Iterable<Customer> findByFirstName(String name);
}