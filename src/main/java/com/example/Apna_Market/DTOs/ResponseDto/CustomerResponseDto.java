package com.example.Apna_Market.DTOs.ResponseDto;

import lombok.*;
import lombok.experimental.FieldDefaults;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder

public class CustomerResponseDto {

    String name;
    String message;
}
