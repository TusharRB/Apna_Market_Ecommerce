package com.example.Apna_Market.Transformer;

import com.example.Apna_Market.DTOs.RequestDto.CustomerRequestDto;
import com.example.Apna_Market.DTOs.ResponseDto.CustomerResponseDto;
import com.example.Apna_Market.Models.Customer;

public class CustomerTransformer {

    public static Customer CustomerRequestDtoToCustomer(CustomerRequestDto customerRequestDto){

        return Customer.builder()
                .name(customerRequestDto.getName())
                .mobNo(customerRequestDto.getMobNo())
                .age(customerRequestDto.getAge())
                .address(customerRequestDto.getAddress())
                .emailId(customerRequestDto.getEmailId())
                .build();
    }

    public static CustomerResponseDto CustomerToCustomerResponseDto(Customer customer){

        return CustomerResponseDto.builder()
                .name(customer.getName())
                .message("Welcome to Apna Bazar !!!"+ customer.getName())
                .build();
    }
}
