package com.example.Apna_Market.Services.impl;

import com.example.Apna_Market.DTOs.RequestDto.OrderRequestDto;
import com.example.Apna_Market.DTOs.ResponseDto.ItemResponseDto;
import com.example.Apna_Market.DTOs.ResponseDto.OrderResponseDto;
import com.example.Apna_Market.Exceptions.InvalidCardException;
import com.example.Apna_Market.Exceptions.InvalidCustomerException;
import com.example.Apna_Market.Models.*;
import com.example.Apna_Market.Repository.CardRepository;
import com.example.Apna_Market.Repository.CustomerRepository;
import com.example.Apna_Market.Repository.OrderedRepository;
import com.example.Apna_Market.Repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class OrderServiceImpl{

    @Autowired
    ProductServiceImpl productService;
    @Autowired
    CustomerRepository customerRepository;
    @Autowired
    ProductRepository productRepository;

    @Autowired
    CardRepository cardRepository;
    @Autowired
    OrderedRepository orderedRepository;


    
    public Ordered placeOrder(Customer customer, Card card) throws Exception {


        Cart cart = customer.getCart();

        Ordered order = new Ordered();

        order.setOrderNo(String.valueOf(UUID.randomUUID()));

        String maskedCardNo = generateMaskedCard(card.getCardNo());




        order.setCardUsed(maskedCardNo);
        order.setCustomer(customer);

        List<Item> orderedItems = new ArrayList<>();


        for(Item item : cart.getItems()){


            try {
                productService.decreaseProductQuantity(item);

                orderedItems.add(item);
            }
            catch (Exception e){
                throw new Exception("Product out of stock !!!!");
            }
        }

        order.setItems(orderedItems);

        for(Item item : orderedItems){

            item.setOrder(order);
        }

        order.setTotalValue(cart.getCartTotal());

        return order;
    }

    public OrderResponseDto placeOrder(OrderRequestDto orderRequestDto) throws Exception {

        Customer customer;
        try{
            customer = customerRepository.findById(orderRequestDto.getCustomerId()).get();
        }
        catch (Exception e){
            throw new InvalidCustomerException("Customer Id is invalid !!");
        }

        Product product;
        try{
            product = productRepository.findById(orderRequestDto.getProductId()).get();
        }
        catch(Exception e){
            throw new Exception("Product doesn't exist");
        }

        Card card = cardRepository.findByCardNo(orderRequestDto.getCardNo());
        if(card==null || card.getCvv()!=orderRequestDto.getCvv() || card.getCustomer()!=customer){
            throw new InvalidCardException("Your card is not valid!!");
        }

        Item item = Item.builder()
                .requiredQuantity(orderRequestDto.getRequiredQuantity())
                .product(product)
                .build();
        try{
            productService.decreaseProductQuantity(item);
        }
        catch (Exception e){
            throw new Exception(e.getMessage());
        }

        Ordered order = new Ordered();
        order.setOrderNo(String.valueOf(UUID.randomUUID()));
        String maskedCardNo = generateMaskedCard(card.getCardNo());
        order.setCardUsed(maskedCardNo);
        order.setCustomer(customer);
        order.setTotalValue(item.getRequiredQuantity()*product.getPrice());
        order.getItems().add(item);

        customer.getOrderedList().add(order);
        item.setOrder(order);
        product.getItemList().add(item);

        Ordered savedOrder = orderedRepository.save(order); // order and item

        OrderResponseDto orderResponseDto = new OrderResponseDto();
        orderResponseDto.setOrderDate(savedOrder.getOrderDate());
        orderResponseDto.setCardUsed(savedOrder.getCardUsed());
        orderResponseDto.setCustomerName(customer.getName());
        orderResponseDto.setOrderNo(savedOrder.getOrderNo());
        orderResponseDto.setTotalValue(savedOrder.getTotalValue());

        List<ItemResponseDto> items = new ArrayList<>();
        for(Item itemEntity: savedOrder.getItems()){
            ItemResponseDto itemResponseDto = new ItemResponseDto();
            itemResponseDto.setPriceOfOneItem(itemEntity.getProduct().getPrice());
            itemResponseDto.setTotalPrice(itemEntity.getRequiredQuantity()*itemEntity.getProduct().getPrice());
            itemResponseDto.setProductName(itemEntity.getProduct().getName());
            itemResponseDto.setQuantity(itemEntity.getRequiredQuantity());

            items.add(itemResponseDto);
        }

        orderResponseDto.setItems(items);
        return orderResponseDto;

    }

    String maskedCardNo = " ";

    public String generateMaskedCard(String cardNo){


        for(int i=0; i < cardNo.length() - 4 ; i++){

            maskedCardNo += "X";
        }

        maskedCardNo += cardNo.substring(cardNo.length() - 4 );

        return maskedCardNo;
    }
}
