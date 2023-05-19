package com.example.Apna_Market.Controllers;


import com.example.Apna_Market.DTOs.RequestDto.CustomerRequestDto;
import com.example.Apna_Market.DTOs.ResponseDto.CustomerResponseDto;
import com.example.Apna_Market.Exceptions.MobileAlreadyPresentException;
import com.example.Apna_Market.Services.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/customer")

public class CustomerController {

    @Autowired
    CustomerService customerService;

    @PostMapping("/add")
    public CustomerResponseDto addCustomer(@RequestBody CustomerRequestDto customerRequestDto) throws MobileAlreadyPresentException {

        return customerService.addCustomer(customerRequestDto);
    }
}
