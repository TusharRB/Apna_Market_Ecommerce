package com.example.Apna_Market.DTOs.ResponseDto;

import com.example.Apna_Market.Enums.ProductStatus;
import lombok.*;
import lombok.experimental.FieldDefaults;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder

public class ProductResponseDto {

    String productName;
    String sellerName;

    int quantity;
    ProductStatus productStatus;

}
