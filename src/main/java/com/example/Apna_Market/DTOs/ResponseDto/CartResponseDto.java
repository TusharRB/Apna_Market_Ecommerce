package com.example.Apna_Market.DTOs.ResponseDto;


import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder

public class CartResponseDto {

    Integer cartTotal;
    Integer numberOfItems;
    String customerName;

    List<ItemResponseDto> items;
}
