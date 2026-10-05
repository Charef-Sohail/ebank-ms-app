package com.enset.cusotmerservice.repository;

import com.enset.cusotmerservice.entities.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
}