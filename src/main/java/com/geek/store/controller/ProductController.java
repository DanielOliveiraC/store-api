package com.geek.store.controller;

import com.geek.store.model.ProductModel;
import com.geek.store.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/products")
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping
    public String showProducts(Model model) {
        List<ProductModel> products = productService.getAllProducts();
        model.addAttribute("products", products);
        return "products";
    }
}
