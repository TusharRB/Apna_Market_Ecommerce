package com.example.Apna_Market.Services.impl;

import com.example.Apna_Market.DTOs.RequestDto.SellerRequestDto;
import com.example.Apna_Market.DTOs.ResponseDto.SellerResponseDto;
import com.example.Apna_Market.Exceptions.EmailAlreadyPresentException;
import com.example.Apna_Market.Models.Seller;
import com.example.Apna_Market.Repository.SellerRepository;
import com.example.Apna_Market.Services.SellerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SellerServiceImpl implements SellerService {

    @Autowired
    SellerRepository sellerRepository;

    @Override
    public SellerResponseDto addSeller(SellerRequestDto sellerRequestDto) throws EmailAlreadyPresentException {

        Seller seller = new Seller();

        seller.setName(sellerRequestDto.getName());
        seller.setAge(sellerRequestDto.getAge());
        seller.setEmailId(sellerRequestDto.getEmailId());
        seller.setMobNo(sellerRequestDto.getMobNo());

        if(sellerRepository.findByEmailId(sellerRequestDto.getEmailId()) != null)
            throw new EmailAlreadyPresentException("Email id Already Registered");

        sellerRepository.save(seller);


        // prepare Response dto

        SellerResponseDto sellerResponseDto = new SellerResponseDto();
        sellerResponseDto.setName(seller.getName());
        sellerResponseDto.setAge(seller.getAge());



        return  sellerResponseDto;


    }

    @Override
    public SellerResponseDto getSellerByEmail(String emailId) {

        Seller seller = sellerRepository.findByEmailId(emailId);

        //prepare Response Dto

        SellerResponseDto sellerResponseDto = new SellerResponseDto();

        sellerResponseDto.setName(seller.getName());
        sellerResponseDto.setAge(seller.getAge());

        return  sellerResponseDto;
    }

    @Override
    public SellerResponseDto getSellerById(int id) {

        Seller seller = sellerRepository.findById(id).get();

        // Prepare Response Dto

        SellerResponseDto sellerResponseDto = new SellerResponseDto();

        sellerResponseDto.setName(seller.getName());
        sellerResponseDto.setAge(seller.getAge());

        return  sellerResponseDto;


    }

    @Override
    public SellerResponseDto updateSellerMobNo(SellerRequestDto sellerRequestDto) throws Exception {

        try {
            Seller seller = sellerRepository.findByEmailId(sellerRequestDto.getEmailId());

            seller.setMobNo(sellerRequestDto.getMobNo());

            sellerRepository.save(seller);

            // Prepare Response Dto

            SellerResponseDto sellerResponseDto = new SellerResponseDto();

            sellerResponseDto.setName(seller.getName());
            sellerResponseDto.setAge(seller.getAge());
            sellerResponseDto.setMobNo(seller.getMobNo());

            return sellerResponseDto;

        } catch (Exception e) {
            throw new Exception("Invalid Seller Email Id");
        }
    }


    @Override
    public String deleteSellerByEmail(String emailId){

        Seller seller = sellerRepository.findByEmailId(emailId);

        if(seller != null){
            sellerRepository.delete(seller);
            return "Seller Deleted Successfully";
        }
        return "Seller Not Found";
    }


    @Override
    public String deleteSellerById(int id) {


        Seller seller = sellerRepository.findById(id).get();

        if(seller != null){
            sellerRepository.deleteById(id);
            return "Seller Deleted Successfully";
        }
        return "Seller Not Found";
    }

    @Override
    public List<SellerResponseDto> getAllSellerByParticularAge(int age) {

        List<Seller> sellerList = sellerRepository.findAll();

        List<SellerResponseDto>  sellers = new ArrayList<>();

        for(Seller seller : sellerList){

            SellerResponseDto sellerResponseDto = new SellerResponseDto();
            sellerResponseDto.setName(seller.getName());
            sellerResponseDto.setAge(seller.getAge());
            sellerResponseDto.setMobNo(seller.getMobNo());

            if(seller.getAge() > age){
                sellers.add(sellerResponseDto);
            }
        }

        return sellers;


    }

}
