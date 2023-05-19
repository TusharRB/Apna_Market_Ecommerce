package com.example.Apna_Market.Services;

import com.example.Apna_Market.DTOs.RequestDto.ProductRequestDto;
import com.example.Apna_Market.DTOs.ResponseDto.ProductResponseDto;
import com.example.Apna_Market.Enums.ProductCategory;
import com.example.Apna_Market.Exceptions.InvalidSellerException;
import com.example.Apna_Market.Models.Item;

import java.util.List;

public interface ProductService {

    public ProductResponseDto addProduct(ProductRequestDto productRequestDto) throws InvalidSellerException;

    public List<ProductResponseDto> getAllProductByCategory(ProductCategory category);

    public void decreaseProductQuantity(Item item) throws Exception;
}
