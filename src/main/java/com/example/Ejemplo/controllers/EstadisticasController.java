package com.example.Ejemplo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.Ejemplo.repositories.DetalleVentaRepository;

@Controller
@RequestMapping("/productos/estadisticas")
public class EstadisticasController {
    @Autowired
    private DetalleVentaRepository detalleVentaRepository;

    @GetMapping
    public String mostrarEstadisticas(Model model) {
        model.addAttribute("productosMasVendidos", detalleVentaRepository.findProductosMasVendidos());
        return "estadisticasProductos";
    }
}
