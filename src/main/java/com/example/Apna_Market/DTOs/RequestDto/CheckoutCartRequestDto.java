package com.example.Apna_Market.DTOs.RequestDto;

import lombok.*;
import lombok.experimental.FieldDefaults;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)

public class CheckoutCartRequestDto {

    int customerId;

    String cardNo;
    int cvv;

}

