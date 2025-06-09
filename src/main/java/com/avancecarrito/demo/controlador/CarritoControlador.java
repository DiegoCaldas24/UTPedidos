package com.avancecarrito.demo.controlador;

import com.avancecarrito.demo.modelo.CodigoVerificacion;
import com.avancecarrito.demo.modelo.Carrito;
import com.avancecarrito.demo.repository.CarritoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class CarritoControlador {

    @Autowired
    private CarritoRepository carritoRepository;

    @GetMapping("/carrito/{idUsuario}")
    public String verCarrito(@PathVariable Integer idUsuario, Model model) {
        model.addAttribute("carrito", carritoRepository.findByUsuarioId(idUsuario));
        List<Carrito> carrito = carritoRepository.findByUsuarioId(idUsuario);
        BigDecimal total = carrito.stream()
                .map(item -> item.getProducto().getPrecio().multiply(BigDecimal.valueOf(item.getCantidad())))
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        model.addAttribute("total", total);
        model.addAttribute("idUsuario", idUsuario);

        return "carrito";

    }

    @GetMapping("/catalogo")
    public String verCatalogo() {
        return "catalogo";
    }

    @GetMapping("/tupedido/{idUsuario}")
    public String verPedido(@PathVariable Integer idUsuario, Model model) {
        // Simulación del código generado
        String codigoPedido = "PED" + (int) (Math.random() * 100000);
        // Obtener carrito por usuario
        List<Carrito> carrito = carritoRepository.findByUsuarioId(idUsuario);
        model.addAttribute("carrito", carrito);

        // Simular hora actual
        String horaPedido = java.time.LocalTime.now().toString().substring(0, 5);

        model.addAttribute("codigoPedido", codigoPedido);
        model.addAttribute("carrito", carrito);
        model.addAttribute("horaPedido", horaPedido);
        return "tupedido";
    }

    @PostMapping("/carrito/{idUsuario}")
    public String procesarPago(
            @PathVariable Integer idUsuario,
            @RequestParam String metodo,
            @RequestParam String telefono,
            @RequestParam String codigo,
            @RequestParam String horaRecojo,
            RedirectAttributes redirect) {
        List<CodigoVerificacion> codigosValidos = Arrays.asList(
                new CodigoVerificacion("987654321", "A1B2C3"),
                new CodigoVerificacion("912345678", "X9Y8Z7"));
        boolean valido = codigosValidos.stream()
                .anyMatch(c -> c.getTelefono().equals(telefono) && c.getCodigo().equalsIgnoreCase(codigo));

        if (valido) {
            redirect.addFlashAttribute("exito", "Pago exitoso.");
            return "redirect:/tupedido/" + idUsuario;
        } else {
            redirect.addFlashAttribute("error", "El código no corresponde con el número de teléfono.");
            redirect.addFlashAttribute("abrirModal", true); // 🔁 para mantener el modal abierto
            return "redirect:/carrito/" + idUsuario;
        }
    }
}
