package com.example.Apna_Market.Transformer;

import com.example.Apna_Market.DTOs.RequestDto.CardRequestDto;
import com.example.Apna_Market.Models.Card;

import java.sql.Date;

public class CardTransformer {

    public static Card CardRequestDtoToCard(CardRequestDto cardRequestDto){

        return Card.builder()
                .cardNo(cardRequestDto.getCardNo())
                .cardType(cardRequestDto.getCardType())
                .cvv(cardRequestDto.getCvv())
                .expiryDate(cardRequestDto.getExpiryDate())
                .build();
    }
}
