package com.example.demo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;
import java.util.List;


@AllArgsConstructor
@Data
public class Product {

    private int productId;
    private String name;
    private int price;
    private String category;
}
