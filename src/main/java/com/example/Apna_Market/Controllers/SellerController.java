package com.example.Apna_Market.Controllers;

import com.example.Apna_Market.DTOs.RequestDto.SellerRequestDto;
import com.example.Apna_Market.DTOs.ResponseDto.SellerResponseDto;
import com.example.Apna_Market.Services.SellerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/seller")
public class SellerController {

    @Autowired
    SellerService sellerService;
    @PostMapping("/add")
    public ResponseEntity addSeller(@RequestBody SellerRequestDto sellerRequestDto) {

        try{

            SellerResponseDto sellerResponseDto = sellerService.addSeller(sellerRequestDto);

            return new ResponseEntity(sellerResponseDto, HttpStatus.CREATED);
       }
        catch(Exception e){

           return new ResponseEntity(e.getMessage(),HttpStatus.BAD_REQUEST);
        }


    }

    // Get Seller By Email

    @GetMapping("/get_seller_by_email")
    public SellerResponseDto getSellerByEmail(@RequestParam("emailId") String emailId){

        return sellerService.getSellerByEmail(emailId);
    }



    // Get Seller By Id

    @GetMapping("/get_seller_by_id")
    public SellerResponseDto getSellerById(@RequestParam("id") int id){

        return sellerService.getSellerById(id);
    }


    // Update Seller MobNo Based on EmailId

    @PutMapping("/update_seller_mobNo")
    public SellerResponseDto updateSellerMobNo(@RequestBody SellerRequestDto sellerRequestDto) throws Exception{

        return sellerService.updateSellerMobNo(sellerRequestDto);
    }



    // delete a seller based on email

    @DeleteMapping("/delete_seller_by_email")
    public String deleteSellerByEmail(@RequestParam("emailId") String emailId){

        return sellerService.deleteSellerByEmail(emailId);
    }




    // delete a seller based on Id

    @DeleteMapping("/delete_seller_by_id")
    public String deleteSellerById(@RequestParam("id") int id){

        return sellerService.deleteSellerById(id);
    }


    // Get All Seller By Particular Age

    @GetMapping("/get_all_Seller_by_age")
    public List<SellerResponseDto> getAllSellerByParticularAge(@RequestParam("age") int age){

        return sellerService.getAllSellerByParticularAge(age);
    }
}
