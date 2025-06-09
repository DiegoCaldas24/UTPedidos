package com.example.Ejemplo.controllers;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.Ejemplo.Services.CarritoService;
import com.example.Ejemplo.Services.ProductoService;
import com.example.Ejemplo.models.Carrito;
import com.example.Ejemplo.models.Producto;
import com.example.Ejemplo.models.Usuario;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/carrito")
public class CarritoController {

    @Autowired
    private CarritoService carritoService;

    @Autowired
    private ProductoService productoService;

    @GetMapping
    public String verCarrito(Model model, HttpSession session) {
        Usuario usuario = (Usuario) session.getAttribute("usuario");
        if (usuario == null) {
            return "redirect:/login";
        }

        List<Carrito> itemsCarrito = carritoService.obtenerCarritoPorUsuario(usuario.getId());
        BigDecimal total = itemsCarrito.stream()
            .map(carritoService::calcularTotal)
            .reduce(BigDecimal.ZERO, BigDecimal::add);

        model.addAttribute("itemsCarrito", itemsCarrito);
        model.addAttribute("total", total);
        return "carrito";
    }

    @PostMapping("/agregar")
    public String agregarAlCarrito(
            @RequestParam Integer idProducto,
            @RequestParam Integer cantidad,
            HttpSession session,
            RedirectAttributes redirectAttributes) {
        
        Usuario usuario = (Usuario) session.getAttribute("usuario");
        if (usuario == null) {
            redirectAttributes.addFlashAttribute("error", "Debe iniciar sesión para agregar productos al carrito");
            return "redirect:/login";
        }

        Producto producto = productoService.findById(idProducto);
        if (producto == null) {
            redirectAttributes.addFlashAttribute("error", "Producto no encontrado");
            return "redirect:/inicio";
        }

        if (cantidad <= 0) {
            redirectAttributes.addFlashAttribute("error", "La cantidad debe ser mayor a 0");
            return "redirect:/inicio";
        }

        if (cantidad > producto.getStock()) {
            redirectAttributes.addFlashAttribute("error", "No hay suficiente stock disponible");
            return "redirect:/inicio";
        }

        carritoService.agregarProducto(usuario.getId(), producto, cantidad);
        redirectAttributes.addFlashAttribute("mensaje", "Producto agregado al carrito");
        return "redirect:/carrito";
    }

    @PostMapping("/eliminar")
    public String eliminarDelCarrito(
            @RequestParam Integer idCarrito,
            HttpSession session,
            RedirectAttributes redirectAttributes) {
        
        Usuario usuario = (Usuario) session.getAttribute("usuario");
        if (usuario == null) {
            return "redirect:/login";
        }

        carritoService.eliminarDelCarrito(idCarrito);
        redirectAttributes.addFlashAttribute("mensaje", "Producto eliminado del carrito");
        return "redirect:/carrito";
    }

    @PostMapping("/vaciar")
    public String vaciarCarrito(
            HttpSession session,
            RedirectAttributes redirectAttributes) {
        
        Usuario usuario = (Usuario) session.getAttribute("usuario");
        if (usuario == null) {
            return "redirect:/login";
        }

        carritoService.vaciarCarritoPorUsuario(usuario.getId());
        redirectAttributes.addFlashAttribute("mensaje", "Carrito vaciado correctamente");
        return "redirect:/carrito";
    }

    @PostMapping("/confirmar")
    public String confirmarPedido(
            HttpSession session,
            RedirectAttributes redirectAttributes) {
        
        Usuario usuario = (Usuario) session.getAttribute("usuario");
        if (usuario == null) {
            return "redirect:/login";
        }

        try {
            carritoService.confirmarPedido(usuario.getId());
            redirectAttributes.addFlashAttribute("mensaje", "Pedido confirmado exitosamente");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Error al confirmar el pedido: " + e.getMessage());
        }
        return "redirect:/carrito";
    }
}