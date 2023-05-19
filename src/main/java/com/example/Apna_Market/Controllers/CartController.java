package com.example.Apna_Market.Controllers;


import com.example.Apna_Market.DTOs.RequestDto.CheckoutCartRequestDto;
import com.example.Apna_Market.DTOs.RequestDto.ItemRequestDto;
import com.example.Apna_Market.DTOs.ResponseDto.CartResponseDto;
import com.example.Apna_Market.DTOs.ResponseDto.OrderResponseDto;
import com.example.Apna_Market.Models.Item;
import com.example.Apna_Market.Services.CartService;
import com.example.Apna_Market.Services.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/cart")

public class CartController {

    @Autowired
    ItemService itemService;

    @Autowired
    CartService cartService;

    @PostMapping("/add")
    public ResponseEntity addToCart(@RequestBody ItemRequestDto itemRequestDto) throws Exception{


        try{
            Item saveditem = itemService.addItem(itemRequestDto);

            CartResponseDto cartResponseDto = cartService.saveCart(itemRequestDto.getCustomerId(),saveditem);

            return new ResponseEntity(cartResponseDto,HttpStatus.ACCEPTED);
        } catch (Exception e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }


    @PostMapping("/checkout")
    public OrderResponseDto checkOutCart(@RequestBody CheckoutCartRequestDto checkoutCartRequestDto) throws Exception {

        return cartService.checkOutCart(checkoutCartRequestDto);
    }
}
