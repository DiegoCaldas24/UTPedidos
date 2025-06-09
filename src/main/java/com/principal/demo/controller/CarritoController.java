package com.principal.demo.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.principal.demo.repository.CarritoRepository;

@Controller
public class CarritoController {
    
     @Autowired
    private CarritoRepository carritoRepository;

    @GetMapping("/carrito/{idUsuario}")
    public String verCarrito(@PathVariable Integer idUsuario, Model model) {
        model.addAttribute("carrito", carritoRepository.findByUsuarioId(idUsuario));
        return "carrito";
    }
}
