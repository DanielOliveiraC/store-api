package com.geek.store.controller;

import com.geek.store.model.ProductModel;
import com.geek.store.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/admin")
@PreAuthorize("hasRole('ROLE_ADMIN')")
public class AdminController {

    @Autowired
    private ProductService productService;

    @GetMapping("/products/register")
    public String showProductRegisterForm(Model model) {
        if (!model.containsAttribute("product")) {
            model.addAttribute("product", new ProductModel());
        }
        return "admin/product-register";
    }

    @PostMapping("/products/register")
    public String registerProduct(@ModelAttribute ProductModel product, 
                                RedirectAttributes redirectAttributes) {
        try {
            productService.createProduct(product);
            redirectAttributes.addFlashAttribute("success", true);
            return "redirect:/admin/products/register";
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Erro ao cadastrar produto: " + e.getMessage());
            redirectAttributes.addFlashAttribute("product", product);
            return "redirect:/admin/products/register";
        }
    }

    @PostMapping("/products/{id}/delete")
    public String deleteProduct(@PathVariable String id, 
                              RedirectAttributes redirectAttributes) {
        try {
            productService.deleteProduct(id);
            redirectAttributes.addFlashAttribute("success", "Produto removido com sucesso!");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Erro ao remover produto: " + e.getMessage());
        }
        return "redirect:/products";
    }

    @PostMapping("/products/{id}/update")
    public String updateProduct(@PathVariable String id, 
                              @ModelAttribute ProductModel product,
                              RedirectAttributes redirectAttributes) {
        try {
            productService.updateProduct(id, product);
            redirectAttributes.addFlashAttribute("success", "Produto atualizado com sucesso!");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Erro ao atualizar produto: " + e.getMessage());
        }
        return "redirect:/products";
    }
}
