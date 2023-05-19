package com.example.Apna_Market.Services.impl;

import com.example.Apna_Market.DTOs.RequestDto.CheckoutCartRequestDto;
import com.example.Apna_Market.DTOs.ResponseDto.CartResponseDto;
import com.example.Apna_Market.DTOs.ResponseDto.ItemResponseDto;
import com.example.Apna_Market.DTOs.ResponseDto.OrderResponseDto;
import com.example.Apna_Market.Exceptions.InvalidCardException;
import com.example.Apna_Market.Exceptions.InvalidCustomerException;
import com.example.Apna_Market.Models.*;
import com.example.Apna_Market.Repository.CardRepository;
import com.example.Apna_Market.Repository.CartRepository;
import com.example.Apna_Market.Repository.CustomerRepository;
import com.example.Apna_Market.Repository.OrderedRepository;
import com.example.Apna_Market.Services.CartService;
import com.example.Apna_Market.Transformer.ItemTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CartServiceImpl implements CartService {

    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    OrderedRepository orderedRepository;

    @Autowired
    OrderService orderService;

    @Autowired
    CartRepository cartRepository;

    @Autowired
    CardRepository cardRepository;

    @Override
    public CartResponseDto saveCart(int customerId, Item item) {

        Customer customer = customerRepository.findById(customerId).get();

        Cart cart = customer.getCart();

        int newTotal = cart.getCartTotal() + item.getRequiredQuantity() * item.getProduct().getPrice();


        cart.setCartTotal(newTotal);

        cart.getItems().add(item);

        cart.setNumberOfItems(cart.getItems().size());

        item.setCart(cart);

        Cart savedCart = cartRepository.save(cart);

        // prepare Response Dto

        CartResponseDto cartResponseDto = CartResponseDto.builder()
                .cartTotal(savedCart.getCartTotal())
                .customerName(customer.getName())
                .numberOfItems(savedCart.getNumberOfItems())
                .build();

        List<ItemResponseDto> itemResponseDtoList = new ArrayList<>();

        for(Item itemEntity:savedCart.getItems()){

            ItemResponseDto itemResponseDto = new ItemResponseDto();

            itemResponseDto.setPriceOfOneItem(itemEntity.getProduct().getPrice());

            itemResponseDto.setTotalPrice(itemEntity.getRequiredQuantity()*itemEntity.getProduct().getPrice());

            itemResponseDto.setProductName(itemEntity.getProduct().getName());

            itemResponseDto.setQuantity(itemEntity.getRequiredQuantity());

            itemResponseDtoList.add(itemResponseDto);
        }

        cartResponseDto.setItems(itemResponseDtoList);

        return cartResponseDto;

    }

    @Override
    public OrderResponseDto checkOutCart(CheckoutCartRequestDto checkoutCartRequestDto) throws Exception {

        // 1st Valid Customer
        Customer customer;

        try{
            customer = customerRepository.findById(checkoutCartRequestDto.getCustomerId()).get();
        }
        catch (Exception e){

            throw new InvalidCustomerException("Customer is Not Valid !!!!");
        }


        // 2nd  Valid Card

        Card card = cardRepository.findByCardNo(checkoutCartRequestDto.getCardNo());

        if(card == null ||
                card.getCvv() != checkoutCartRequestDto.getCvv() ||
                card.getCustomer()  != customer){


            throw new InvalidCardException("Card is Not Valid !!!!!");

        }


        // 3rd Cart is Empty

        Cart cart = customer.getCart();

        if(cart.getNumberOfItems() == 0){

            throw new Exception("Cart is Empty");
        }


        try{
            Ordered order = orderService.placeOrder(customer,card);  // throw exception if product goes out of stock
            customer.getOrderedList().add(order);
            resetCart(cart);
            Ordered savedOrder = orderedRepository.save(order);

            // prepare response dto
            OrderResponseDto orderResponseDto = new OrderResponseDto();
            orderResponseDto.setOrderDate(savedOrder.getOrderDate());
            orderResponseDto.setCardUsed(savedOrder.getCardUsed());
            orderResponseDto.setCustomerName(customer.getName());
            orderResponseDto.setOrderNo(savedOrder.getOrderNo());
            orderResponseDto.setTotalValue(savedOrder.getTotalValue());

            List<ItemResponseDto> items = new ArrayList<>();
            for(Item itemEntity: savedOrder.getItems()){
                ItemResponseDto itemResponseDto = ItemTransformer.ItemToItemResponseDto(itemEntity);
                items.add(itemResponseDto);
            }
            orderResponseDto.setItems(items);
            return orderResponseDto;
        }
        catch (Exception e){
            throw new Exception(e.getMessage());
        }
    }

    public void resetCart(Cart cart) {

        cart.setCartTotal(0);

        for(Item item : cart.getItems()){

            item.setCart(null);
        }

        cart.setNumberOfItems(0);

        cart.getItems().clear();
    }
}
