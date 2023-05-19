package com.example.Apna_Market.Transformer;

import com.example.Apna_Market.DTOs.RequestDto.ItemRequestDto;
import com.example.Apna_Market.DTOs.ResponseDto.ItemResponseDto;
import com.example.Apna_Market.Models.Item;

public class ItemTransformer {

    public static Item ItemRequestDtoToItem(ItemRequestDto itemRequestDto){

        return Item.builder()
                .requiredQuantity(itemRequestDto.getRequiredQuantity())
                .build();
    }

    public static ItemResponseDto ItemToItemResponseDto(Item item){

        return ItemResponseDto.builder()
                .priceOfOneItem(item.getProduct().getPrice())
                .productName(item.getProduct().getName())
                .quantity(item.getRequiredQuantity())
                .totalPrice(item.getRequiredQuantity() * item.getProduct().getPrice())
                .build();
    }
}
