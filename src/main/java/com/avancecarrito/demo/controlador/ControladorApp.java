package com.avancecarrito.demo.controlador;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.ui.Model;

@Controller
public class ControladorApp {

    @GetMapping("/carrito")
    public String mostrarCarrito(Model model) {
        return "carrito";

    }
}
