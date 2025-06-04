package com.principal.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class PedidoController {

    @PostMapping("/pedido/registrar")
    public String registrarPedido(@RequestParam String nombreProducto, @RequestParam int cantidad) {
        System.out.println("Pedido recibido: " + nombreProducto + " - Cantidad: " + cantidad);
        // Aquí podrías almacenar el pedido en una lista temporal o simplemente mostrar un mensaje
        return "redirect:/catalogo";
    }
}
