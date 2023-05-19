package com.example.Apna_Market.DTOs.ResponseDto;


import lombok.*;
import lombok.experimental.FieldDefaults;

import java.sql.Date;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)

public class OrderResponseDto {

    String orderNo;
    String customerName;
    int totalValue;

    Date orderDate;

    String cardUsed;

    List<ItemResponseDto> items;

}
