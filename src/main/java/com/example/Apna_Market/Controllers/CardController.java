package com.example.Apna_Market.Controllers;

import com.example.Apna_Market.DTOs.RequestDto.CardRequestDto;
import com.example.Apna_Market.DTOs.ResponseDto.CardResponseDto;
import com.example.Apna_Market.DTOs.ResponseDto.CustomerResponseDto;
import com.example.Apna_Market.Exceptions.InvalidCustomerException;
import com.example.Apna_Market.Services.CardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/card")

public class CardController {

    @Autowired
    CardService cardService;

    @PostMapping("/add")
    public ResponseEntity addCard(@RequestBody CardRequestDto cardRequestDto) {


       try{

           CardResponseDto cardResponseDto = cardService.addCard(cardRequestDto);

           return new ResponseEntity(cardResponseDto, HttpStatus.CREATED);
       }

       catch(InvalidCustomerException e){

           return new ResponseEntity(e.getMessage(),HttpStatus.BAD_REQUEST);
       }
    }

}
