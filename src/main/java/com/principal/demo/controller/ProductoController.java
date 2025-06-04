package com.principal.demo.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.principal.demo.model.Cupon;
import com.principal.demo.model.Producto;

@Controller
public class ProductoController {

@GetMapping("/catalogo")
public String mostrarCatalogo(@RequestParam(required = false) String categoria, Model model) {
    List<Producto> productos = new ArrayList<>();
    productos.add(new Producto("Pollo a la plancha", 10.00, "/imagenes/pollo.png", 4.5, "Menú"));
    productos.add(new Producto("Tallarines verdes", 9.00, "/imagenes/tallarinesverdes.png", 4.2, "Menú"));
    productos.add(new Producto("Ensalada mixta", 6.50, "/imagenes/ensalada.png", 4.7, "Menú"));
    productos.add(new Producto("Lomo saltado", 12.00, "/imagenes/lomoSalteado.png", 4.8, "Menú"));
    productos.add(new Producto("Sopa criolla", 7.50, "/imagenes/sopaCriolla.png", 4.1, "Menú"));
    productos.add(new Producto("Chaufa", 10.00, "/imagenes/chaufa.png", 4.5, "Menú"));


    List<Producto> productosFiltrados = new ArrayList<>();

    if (categoria != null) {
        for (Producto p : productos) {
            if (p.getCategoria().equalsIgnoreCase(categoria)) {
                productosFiltrados.add(p);
            }
        }
    } else {
        productosFiltrados = productos;
    }

     List<Cupon> cupones = List.of(
        new Cupon("Promo 2x1 en combos", "2X1COMBO", "/cupones/cupon1.png"),
        new Cupon("Descuento del 50%", "MITADPRECIO", "/cupones/cupon2.png")
    );

    model.addAttribute("productos", productosFiltrados);
     model.addAttribute("cupones", cupones);
    return "catalogo";
}
}
