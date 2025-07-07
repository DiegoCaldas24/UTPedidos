package com.principal.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.principal.demo.model.Producto;
import com.principal.demo.services.MenuDiaServiceImpl;

@Controller
public class MenuDiaController {

    @Autowired
    private MenuDiaServiceImpl menuDiaServiceImpl;

    @GetMapping("/menuDia")
    public String menuDia(
            @RequestParam(defaultValue = "MENU ECONOMICO") String categoria,
            @RequestParam(defaultValue = "0") int page,
            Model model) {

        int pageSize = 6; // Cambia este número si quieres mostrar más o menos productos por página
        Pageable pageable = PageRequest.of(page, pageSize);

        Page<Producto> productosPage = menuDiaServiceImpl.obtenerProductosDelMenuDeHoyPorCategoria(categoria, pageable);

        model.addAttribute("productos", productosPage.getContent());
        model.addAttribute("productosPage", productosPage);
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", productosPage.getTotalPages());
        model.addAttribute("categoriaActual", categoria);

        return "menuDia";
    }
}
