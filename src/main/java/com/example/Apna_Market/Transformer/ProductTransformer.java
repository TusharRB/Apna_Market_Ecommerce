package com.example.Apna_Market.Transformer;

import com.example.Apna_Market.DTOs.RequestDto.ProductRequestDto;
import com.example.Apna_Market.DTOs.ResponseDto.ProductResponseDto;
import com.example.Apna_Market.Enums.ProductStatus;
import com.example.Apna_Market.Models.Product;

public class ProductTransformer {

    public static Product ProductRequestDtoToProduct(ProductRequestDto productRequestDto){


        return Product.builder()
                .name(productRequestDto.getProductName())
                .price(productRequestDto.getPrice())
                .productCategory(productRequestDto.getProductCategory())
                .quantity(productRequestDto.getQuantity())
                .productStatus(ProductStatus.AVAILABLE)
                .build();
    }

    public static ProductResponseDto ProductToProductResponseDto(Product product){

        return ProductResponseDto.builder()
                .productName(product.getName())
                .sellerName(product.getSeller().getName())
                .quantity(product.getQuantity())
                .productStatus(product.getProductStatus())
                .build();

    }
}
