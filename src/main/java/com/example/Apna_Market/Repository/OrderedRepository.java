package com.example.Apna_Market.Repository;

import com.example.Apna_Market.Models.Ordered;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends JpaRepository<Ordered,Integer> {
}
