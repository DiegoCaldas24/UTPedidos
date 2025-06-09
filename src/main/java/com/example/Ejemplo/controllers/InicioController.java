package com.example.Ejemplo.controllers;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.Ejemplo.Services.ProductoService;
import com.example.Ejemplo.models.DetallePedido;
import com.example.Ejemplo.models.Pedido;
import com.example.Ejemplo.models.Producto;
import com.example.Ejemplo.models.Usuario;
import com.example.Ejemplo.repositories.PedidoRepository;

import jakarta.servlet.http.HttpSession;

@Controller
public class InicioController {

    @Autowired
    private ProductoService productoService;

    @Autowired
    private PedidoRepository pedidoRepository;

    @GetMapping({"/", "/inicio"})
    public String mostrarInicio(Model model) {
        model.addAttribute("productos", productoService.findAll().stream()
            .filter(Producto::getEstado).toList());
        return "Inicio";
    }

    @PostMapping("/pedido/registrar")
    public String registrarPedido(
            @RequestParam String nombreProducto,
            @RequestParam int cantidad,
            RedirectAttributes redirectAttributes,
            HttpSession session) {

        // Obtener el usuario de la sesión
        Usuario usuario = (Usuario) session.getAttribute("usuario");
        if (usuario == null) {
            redirectAttributes.addFlashAttribute("error", "Debe iniciar sesión para realizar un pedido");
            return "redirect:/inicio";
        }

        // Buscar producto por nombre usando el servicio
        Producto producto = productoService.findAll().stream()
            .filter(p -> p.getNombre().toLowerCase().contains(nombreProducto.toLowerCase()))
            .findFirst().orElse(null);

        if (producto == null) {
            redirectAttributes.addFlashAttribute("error", "Producto no encontrado");
            return "redirect:/inicio";
        }

        Pedido pedido = new Pedido();
        pedido.setUsuario(usuario);
        pedido.setFechaPedido(LocalDateTime.now());
        pedido.setFechaEntrega(LocalDateTime.now().plusDays(1)); // Ejemplo: entrega al día siguiente
        pedido.setEstado(true);
        
        // Crear el detalle del pedido
        DetallePedido detalle = new DetallePedido();
        detalle.setPedido(pedido);
        detalle.setProducto(producto);
        detalle.setCantidad(cantidad);
        detalle.setPrecio(producto.getPrecio());
        detalle.setSubtotal(producto.getPrecio().multiply(new BigDecimal(cantidad)));
        
        // Agregar el detalle al pedido
        pedido.getDetalles().add(detalle);

        pedidoRepository.save(pedido);

        redirectAttributes.addFlashAttribute("mensaje", "¡Producto agregado al carrito!");
        return "redirect:/inicio";
    }
}