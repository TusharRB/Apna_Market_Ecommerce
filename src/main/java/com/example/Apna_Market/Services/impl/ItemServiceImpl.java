package com.example.Apna_Market.Services.impl;

import com.example.Apna_Market.DTOs.RequestDto.ItemRequestDto;
import com.example.Apna_Market.Enums.ProductStatus;
import com.example.Apna_Market.Exceptions.InvalidCustomerException;
import com.example.Apna_Market.Models.Customer;
import com.example.Apna_Market.Models.Item;
import com.example.Apna_Market.Models.Product;
import com.example.Apna_Market.Repository.CustomerRepository;
import com.example.Apna_Market.Repository.ItemRepository;
import com.example.Apna_Market.Repository.ProductRepository;
import com.example.Apna_Market.Services.ItemService;
import com.example.Apna_Market.Transformer.ItemTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service

public class ItemServiceImpl implements ItemService {

    @Autowired
    CustomerRepository customerRepository;
    @Autowired
    ProductRepository productRepository;

    @Autowired
    ItemRepository itemRepository;

    @Override
    public ResponseEntity addToCart(ItemRequestDto itemRequestDto) {
        return null;
    }

    @Override
    public Item addItem(ItemRequestDto itemRequestDto) throws Exception {


        // 1st you check customer , customer is valid or not

        Customer customer;

        try{

            customer = customerRepository.findById(itemRequestDto.getCustomerId()).get();
        }
        catch (Exception e){

            throw new InvalidCustomerException("Customer Id is not Valid !!! ");
        }


        // 2nd you check product , product is valid or not also available or not

        Product product;

        try{
            product = productRepository.findById(itemRequestDto.getProductId()).get();
        }
        catch (Exception e){
            throw new Exception("Product doesn't exist !!!");
        }



        // 3rd you check quantity of product


        if(itemRequestDto.getRequiredQuantity() > product.getQuantity()
                ||  product.getProductStatus() != ProductStatus.AVAILABLE){

            throw new Exception(" Product is out of stocks !!! ");

        }

        Item item = ItemTransformer.ItemRequestDtoToItem(itemRequestDto);

        //System.out.println(customer.getCart().getItems().size());

        //item.setCart(customer.getCart());
        item.setProduct(product);

        product.getItemList().add(item);

       // Product savedProduct = productRepository.save(product);

        // Think - Only saving child will also work here  ??????????????


        // return itemRepository.save(item);

        // int size = product.getItemList().size();


        return itemRepository.save(item);


    }
}
