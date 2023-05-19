package com.example.Apna_Market.Repository;

import com.example.Apna_Market.Enums.ProductCategory;
import com.example.Apna_Market.Models.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product,Integer> {

    List<Product> findByProductCategory(ProductCategory productCategory);
}
