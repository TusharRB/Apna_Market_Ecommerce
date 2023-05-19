package com.example.Apna_Market.Services.impl;

import com.example.Apna_Market.DTOs.RequestDto.CardRequestDto;
import com.example.Apna_Market.DTOs.ResponseDto.CardResponseDto;
import com.example.Apna_Market.Exceptions.InvalidCustomerException;
import com.example.Apna_Market.Models.Card;
import com.example.Apna_Market.Models.Customer;
import com.example.Apna_Market.Repository.CustomerRepository;
import com.example.Apna_Market.Services.CardService;
import com.example.Apna_Market.Transformer.CardTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class CardServiceImpl implements CardService {

    @Autowired
    CustomerRepository customerRepository;



    @Override
    public CardResponseDto addCard(CardRequestDto cardRequestDto) throws InvalidCustomerException {


        Customer customer = customerRepository.findByMobNo(cardRequestDto.getMobNo());
        if(customer==null){
            throw new InvalidCustomerException("Sorry! The customer doesn't exists");
        }

        Card card = CardTransformer.CardRequestDtoToCard(cardRequestDto);
        card.setCustomer(customer);

        customer.getCards().add(card);
        customerRepository.save(customer);

        // response dto
        return CardResponseDto.builder()
                .customerName(customer.getName())
                .cardNo(card.getCardNo())
                .build();
    }
}
