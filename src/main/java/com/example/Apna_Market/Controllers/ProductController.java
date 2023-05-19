package com.example.Apna_Market.Controllers;

import com.example.Apna_Market.DTOs.RequestDto.ProductRequestDto;
import com.example.Apna_Market.DTOs.ResponseDto.ProductResponseDto;
import com.example.Apna_Market.Enums.ProductCategory;
import com.example.Apna_Market.Exceptions.InvalidSellerException;
import com.example.Apna_Market.Services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/product")

public class ProductController {

    @Autowired
    ProductService productService;

    @PostMapping("/add")
    public ProductResponseDto addProduct(@RequestBody ProductRequestDto productRequestDto) throws InvalidSellerException {

        return productService.addProduct(productRequestDto);
    }

    // Get All Products of Particular Category

    @GetMapping("/get/{category}")
    public List<ProductResponseDto> getAllProductByCategory(@PathVariable("category") ProductCategory category){

        return productService.getAllProductByCategory(category);
    }

    // get all product by seller email
    // delete a product by seller id
    // return top 5 cheapest products
    // return top 5 cost products
    // return all out of stocks products
    // return all available products
    // return all products that have quantity is less than 10.
    // return the cheapest product in particular category.
    // return the costliest product in particular category.
}
