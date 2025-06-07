package com.example.Ejemplo.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.Ejemplo.repositories.DetalleVentaRepository;

@RestController
@RequestMapping("/api/estadisticas")
public class EstadisticaProductoRestController {
    @Autowired
    private DetalleVentaRepository detalleVentaRepository;

    @GetMapping("/masVendidos")
    public List<?> getProductosMasVendidos() {
        return detalleVentaRepository.findProductosMasVendidos();
    }
}
