package com.example.Apna_Market.DTOs.RequestDto;

import com.example.Apna_Market.Enums.ProductCategory;
import lombok.*;
import lombok.experimental.FieldDefaults;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)

public class ProductRequestDto {

    int sellerId;
    String productName;
    int price;
    int quantity;
    ProductCategory productCategory;
}
