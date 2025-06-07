package com.avancecarrito.demo.controlador;

import com.avancecarrito.demo.modelo.Carrito;
import com.avancecarrito.demo.repository.CarritoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class CarritoControlador {

    @Autowired
    private CarritoRepository carritoRepository;

    @GetMapping("/carrito/{idUsuario}")
    public String verCarrito(@PathVariable Integer idUsuario, Model model) {
        model.addAttribute("carrito", carritoRepository.findByUsuarioId(idUsuario));
        return "carrito";
    }
}
