package com.example.Apna_Market.Services;

import com.example.Apna_Market.DTOs.RequestDto.SellerRequestDto;
import com.example.Apna_Market.DTOs.ResponseDto.SellerResponseDto;
import com.example.Apna_Market.Exceptions.EmailAlreadyPresentException;

import java.util.List;

public interface SellerService {

    public SellerResponseDto addSeller(SellerRequestDto sellerRequestDto) throws EmailAlreadyPresentException;


   public SellerResponseDto getSellerByEmail(String emailId);

    public SellerResponseDto getSellerById(int id);

    public SellerResponseDto updateSellerMobNo(SellerRequestDto sellerRequestDto) throws Exception;

   public String deleteSellerByEmail(String emailId);

   public String deleteSellerById(int id);

    public List<SellerResponseDto> getAllSellerByParticularAge(int age);
}
