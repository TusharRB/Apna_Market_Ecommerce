package com.example.Apna_Market.DTOs.ResponseDto;


import lombok.*;
import lombok.experimental.FieldDefaults;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)

public class SellerResponseDto {

    String name;
    int age;

    int mobNo;
}
