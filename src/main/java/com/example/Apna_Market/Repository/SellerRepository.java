package com.example.Apna_Market.Repository;

import com.example.Apna_Market.Models.Seller;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SellerRepository extends JpaRepository<Seller,Integer> {

    Seller findByEmailId(String email);

}
