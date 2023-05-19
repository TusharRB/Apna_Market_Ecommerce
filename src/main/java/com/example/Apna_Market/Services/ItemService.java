package com.example.Apna_Market.Services;

import com.example.Apna_Market.DTOs.RequestDto.ItemRequestDto;
import com.example.Apna_Market.Models.Item;
import org.springframework.http.ResponseEntity;

public interface ItemService {
    public ResponseEntity addToCart(ItemRequestDto itemRequestDto);

    public Item addItem(ItemRequestDto itemRequestDto) throws Exception;
}
