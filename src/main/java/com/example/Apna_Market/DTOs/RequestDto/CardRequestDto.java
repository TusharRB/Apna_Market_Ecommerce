package com.example.Apna_Market.DTOs.RequestDto;

import com.example.Apna_Market.Enums.CardType;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.sql.Date;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)

public class CardRequestDto {


    Integer mobNo;
    String cardNo;
    int cvv;

    Date expiryDate;

    CardType cardType;
}
