package com.example.Apna_Market.Repository;

import com.example.Apna_Market.Models.Card;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CardRepository extends JpaRepository<Card, Integer> {

    Card findByCardNo(String cardNo);


}
