package com.example.Apna_Market.Services;

import com.example.Apna_Market.DTOs.RequestDto.CardRequestDto;
import com.example.Apna_Market.DTOs.ResponseDto.CardResponseDto;
import com.example.Apna_Market.Exceptions.InvalidCustomerException;

public interface CardService {
    public CardResponseDto addCard(CardRequestDto cardRequestDto) throws InvalidCustomerException;
}
