package com.principal.demo.controller;

import com.principal.demo.model.Carrito;
import com.principal.demo.model.Pedido;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.principal.demo.services.CarritoServiceImpl;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("carrito")
public class CarritoController {

    @Autowired
    private CarritoServiceImpl carritoServiceImpl;

    @GetMapping("/{idUsuario}")
    public String verCarrito(@PathVariable Integer idUsuario, Model model) {
        model.addAttribute("carrito", carritoServiceImpl.obtenerCarritosPorUsuario(idUsuario));
        return "carrito";
    }

    @PostMapping("/eliminar/{idUsuario}/{idProducto}")
    public String eliminarDelCarrito(@PathVariable int idProducto,
            @PathVariable int idUsuario,
            RedirectAttributes redirectAttributes) {
        try {
            carritoServiceImpl.eliminarProductoAgregado(idUsuario, idProducto);
            redirectAttributes.addFlashAttribute("success", "Producto eliminado correctamente");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Error al eliminar el producto: " + e.getMessage());
        }

        return "redirect:/carrito/1";
    }

}
