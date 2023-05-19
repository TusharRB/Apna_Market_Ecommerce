package com.example.Apna_Market.Services.impl;

import com.example.Apna_Market.DTOs.RequestDto.CustomerRequestDto;
import com.example.Apna_Market.DTOs.ResponseDto.CustomerResponseDto;
import com.example.Apna_Market.Exceptions.MobileAlreadyPresentException;
import com.example.Apna_Market.Models.Cart;
import com.example.Apna_Market.Models.Customer;
import com.example.Apna_Market.Repository.CustomerRepository;
import com.example.Apna_Market.Services.CustomerService;
import com.example.Apna_Market.Transformer.CustomerTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service

public class CustomerServiceImpl implements CustomerService {

    @Autowired
    CustomerRepository customerRepository;

    @Override
    public CustomerResponseDto addCustomer(CustomerRequestDto customerRequestDto) throws MobileAlreadyPresentException {

        if(customerRepository.findByMobNo(customerRequestDto.getMobNo()) != null)

            throw new MobileAlreadyPresentException("Sorry !!  Customer Already  Exists !!!");


        // Request Dto to customer

        Customer customer = CustomerTransformer.CustomerRequestDtoToCustomer((customerRequestDto));

        Cart cart = Cart.builder()
                .cartTotal(0)
                .numberOfItems(0)
                .customer(customer)
                .build();

        customer.setCart(cart);

        Customer savedCustomer = customerRepository.save(customer);  // Saved Customer and Card

        // Prepare Response Dto

        return CustomerTransformer.CustomerToCustomerResponseDto(savedCustomer);
    }
}
