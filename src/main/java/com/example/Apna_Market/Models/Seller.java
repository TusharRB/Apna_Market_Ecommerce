package com.example.Apna_Market.Models;


import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="seller")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)


public class Seller {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;


    String name;
    @Column(unique = true)
    String emailId;
    int age;

    int mobNo;

    @OneToMany(mappedBy = "seller",cascade = CascadeType.ALL)
    List<Product> products = new ArrayList<>();
}
