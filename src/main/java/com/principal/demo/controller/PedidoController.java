package com.principal.demo.controller;

import com.principal.demo.model.Pedido;
import com.principal.demo.model.Usuario;
import com.principal.demo.services.CarritoService;
import com.principal.demo.services.CarritoServiceImpl;
import com.principal.demo.services.PedidoService;
import com.principal.demo.services.PedidoServiceImpl;
import com.principal.demo.services.UsuarioServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalTime;
import java.util.List;

@Controller
public class PedidoController {

    @Autowired
    private PedidoServiceImpl pedidoServiceImpl;
    @Autowired
    private UsuarioServiceImpl usuarioServiceImpl;
    @Autowired
    private CarritoServiceImpl carritoServiceImpl;


    @PostMapping("/pedido/registrar")
    public String registrarPedido(@RequestParam String nombreProducto, @RequestParam int cantidad) {
        System.out.println("Pedido recibido: " + nombreProducto + " - Cantidad: " + cantidad);
        // Aquí podrías almacenar el pedido en una lista temporal o simplemente mostrar
        // un mensaje
        return "redirect:/catalogo";
    }

    @GetMapping("/pedido/{idUsuario}")
    public String verPagos(@PathVariable Integer idUsuario, Model model) {
        model.addAttribute("pedidos", pedidoServiceImpl.findByUsuario_Id(idUsuario));
        return "pedido";
    }

    @PostMapping("/pedir")
    public String pedir(@RequestParam("idUsuario") int idUsuario, @RequestParam("horaEntrega") LocalTime horaEntrega,
            Model model) {
        Pedido pedido = new Pedido();
        pedido.setCodigo(pedidoServiceImpl.generarCodigoUnico());
        pedido.setUsuario(usuarioServiceImpl.findUsuarioById(idUsuario).orElse(null));
        pedido.setFechaEntrega(horaEntrega);
        pedidoServiceImpl.guardarPedido(pedido);
        carritoServiceImpl.limpiarCarrito(idUsuario);
        return "redirect:/pedido/1";
    }

}
