package com.example.Apna_Market.DTOs.ResponseDto;

import lombok.*;
import lombok.experimental.FieldDefaults;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder

public class ItemResponseDto {

    String productName;
    int priceOfOneItem;
    int totalPrice;
    int quantity;
}
