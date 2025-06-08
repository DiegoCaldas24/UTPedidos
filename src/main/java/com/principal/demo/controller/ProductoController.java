package com.principal.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.principal.demo.model.Producto;
import com.principal.demo.services.ProductoServiceImpl;

@Controller
public class ProductoController {
    @Autowired
    public ProductoServiceImpl productosServiceImpl;
    @GetMapping("/catalogo")
    public String index(@RequestParam(required = false) String categoria,Model model) {
        List<Producto> productos = productosServiceImpl.findAllProductos();
        
        if(categoria != null && !categoria.isEmpty()){
            productos = productosServiceImpl.obtenerProductosPorCategoria(categoria);
        }

        model.addAttribute("productos",productos);
        return "catalogo";
    }
    
}