package com.example.Apna_Market.DTOs.RequestDto;

import lombok.*;
import lombok.experimental.FieldDefaults;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder

public class ItemRequestDto {

    int customerId;
    int productId;
    int requiredQuantity;
}
