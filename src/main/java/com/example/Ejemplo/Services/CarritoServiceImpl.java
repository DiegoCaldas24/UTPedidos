package com.example.Ejemplo.Services;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.Ejemplo.models.Carrito;
import com.example.Ejemplo.models.Producto;
import com.example.Ejemplo.models.Usuario;
import com.example.Ejemplo.repositories.CarritoRepository;
import com.example.Ejemplo.repositories.UsuarioRepository;

@Service
public class CarritoServiceImpl implements CarritoService {

    @Autowired
    private CarritoRepository carritoRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Override
    public List<Carrito> obtenerCarritoPorUsuario(Integer idUsuario) {
        return carritoRepository.findByUsuarioId(idUsuario);
    }

    @Override
    public void agregarAlCarrito(Carrito carrito) {
        carritoRepository.save(carrito);
    }

    @Override
    public void eliminarDelCarrito(Integer idCarrito) {
        carritoRepository.deleteById(idCarrito);
    }

    @Override
    public void vaciarCarritoPorUsuario(Integer idUsuario) {
        List<Carrito> itemsCarrito = carritoRepository.findByUsuarioId(idUsuario);
        carritoRepository.deleteAll(itemsCarrito);
    }

    @Override
    public void confirmarPedido(Integer usuarioId) {
        // Implementación pendiente
        // Aquí se implementará la lógica para confirmar el pedido
    }

    @Override
    public void agregarProducto(Integer usuarioId, Producto producto, int cantidad) {
        Usuario usuario = usuarioRepository.findById(usuarioId)
            .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        Carrito carrito = new Carrito();
        carrito.setUsuario(usuario);
        carrito.setProducto(producto);
        carrito.setCantidad(cantidad);
        carrito.setTotal(producto.getPrecio().multiply(new BigDecimal(cantidad)));
        
        carritoRepository.save(carrito);
    }

    @Override
    public BigDecimal calcularTotal(Carrito carrito) {
        return carrito.getProducto().getPrecio().multiply(new BigDecimal(carrito.getCantidad()));
    }
}