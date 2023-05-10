package com.example.Apna_Market.DTOs.RequestDto;

import lombok.*;
import lombok.experimental.FieldDefaults;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)

public class SellerRequestDto {

    String name;
    String emailId;
    int age;

    int mobNo;
}
