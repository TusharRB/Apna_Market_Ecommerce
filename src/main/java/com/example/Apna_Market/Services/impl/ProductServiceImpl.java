package com.example.Apna_Market.Services.impl;

import com.example.Apna_Market.DTOs.RequestDto.ProductRequestDto;
import com.example.Apna_Market.DTOs.ResponseDto.ProductResponseDto;
import com.example.Apna_Market.Enums.ProductCategory;
import com.example.Apna_Market.Enums.ProductStatus;
import com.example.Apna_Market.Exceptions.InvalidSellerException;
import com.example.Apna_Market.Models.Item;
import com.example.Apna_Market.Models.Product;
import com.example.Apna_Market.Models.Seller;
import com.example.Apna_Market.Repository.ProductRepository;
import com.example.Apna_Market.Repository.SellerRepository;
import com.example.Apna_Market.Services.ProductService;
import com.example.Apna_Market.Transformer.ProductTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {


    @Autowired
    SellerRepository sellerRepository;

    @Autowired
    ProductRepository productRepository;

    @Override
    public ProductResponseDto addProduct(ProductRequestDto productRequestDto) throws InvalidSellerException {

        Seller seller;

        try {
            seller = sellerRepository.findById(productRequestDto.getSellerId()).get();
        } catch (Exception e) {

            throw new InvalidSellerException("Seller Doesn't not exists");
        }


        Product product = ProductTransformer.ProductRequestDtoToProduct(productRequestDto);
        product.setSeller(seller);


        // add product to current products of seller
        seller.getProducts().add(product);
        sellerRepository.save(seller);  // saves both seller and product

        // prepare Response Dto
        return ProductTransformer.ProductToProductResponseDto(product);
    }

    @Override
    public List<ProductResponseDto> getAllProductByCategory(ProductCategory category) {

        List<Product> products = productRepository.findByProductCategory(category);

        List<ProductResponseDto> productResponseDtos = new ArrayList<>();

        for(Product product:products){

            productResponseDtos.add(ProductTransformer.ProductToProductResponseDto(product));
        }

        return  productResponseDtos;
    }

    @Override
    public void decreaseProductQuantity(Item item) throws Exception {


        Product product = item.getProduct();

        int quantity = item.getRequiredQuantity();

        int currentQuantity = product.getQuantity();

        if(quantity > currentQuantity){

            throw new Exception("Product Out of Stock !!!!");
        }

        product.setQuantity(currentQuantity - quantity);

        if(product.getQuantity() == 0){

            product.setProductStatus(ProductStatus.OUT_OF_STOCK);
        }
    }
}
