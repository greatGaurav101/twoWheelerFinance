package com.rupyy.repository;

import com.rupyy.entity.Dealer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DealerRepository extends JpaRepository<Dealer, Integer> {
}