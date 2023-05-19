package com.example.Apna_Market.DTOs.RequestDto;

import lombok.*;
import lombok.experimental.FieldDefaults;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)

public class CustomerRequestDto {

    String name;
    String emailId;
    Integer age;
    Integer mobNo;
    String address;
}
