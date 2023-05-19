package com.example.Apna_Market.Services;

import com.example.Apna_Market.DTOs.RequestDto.CustomerRequestDto;
import com.example.Apna_Market.DTOs.ResponseDto.CustomerResponseDto;
import com.example.Apna_Market.Exceptions.MobileAlreadyPresentException;

public interface CustomerService {
    public CustomerResponseDto addCustomer(CustomerRequestDto customerRequestDto) throws MobileAlreadyPresentException;
}
