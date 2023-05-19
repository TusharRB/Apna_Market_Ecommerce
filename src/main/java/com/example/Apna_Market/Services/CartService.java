package com.example.Apna_Market.Services;

import com.example.Apna_Market.DTOs.RequestDto.CheckoutCartRequestDto;
import com.example.Apna_Market.DTOs.ResponseDto.CartResponseDto;
import com.example.Apna_Market.DTOs.ResponseDto.OrderResponseDto;
import com.example.Apna_Market.Exceptions.InvalidCardException;
import com.example.Apna_Market.Exceptions.InvalidCustomerException;
import com.example.Apna_Market.Models.Cart;
import com.example.Apna_Market.Models.Item;

public interface CartService {
    public CartResponseDto saveCart(int customerId, Item saveditem);

    public OrderResponseDto checkOutCart(CheckoutCartRequestDto checkoutCartRequestDto) throws Exception;
    public void resetCart(Cart cart);
}
