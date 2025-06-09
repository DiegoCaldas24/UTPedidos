package com.example.Ejemplo.Services;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.Ejemplo.models.Carrito;
import com.example.Ejemplo.models.DetallePedido;
import com.example.Ejemplo.models.DetallePedidoId;
import com.example.Ejemplo.models.Pedido;
import com.example.Ejemplo.models.Producto;
import com.example.Ejemplo.models.Usuario;
import com.example.Ejemplo.repositories.CarritoRepository;
import com.example.Ejemplo.repositories.DetallePedidoRepository;
import com.example.Ejemplo.repositories.PedidoRepository;
import com.example.Ejemplo.repositories.UsuarioRepository;

@Service
public class CarritoServiceImpl implements CarritoService {

    @Autowired
    private CarritoRepository carritoRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private PedidoRepository pedidoRepository;

    @Autowired
    private DetallePedidoRepository detallePedidoRepository;

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
    @Transactional
    public void confirmarPedido(Integer usuarioId) {
        Usuario usuario = usuarioRepository.findById(usuarioId)
            .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        List<Carrito> itemsCarrito = carritoRepository.findByUsuarioId(usuarioId);
        if (itemsCarrito.isEmpty()) {
            throw new RuntimeException("El carrito está vacío");
        }

        // Crear el pedido
        Pedido pedido = new Pedido();
        pedido.setUsuario(usuario);
        pedido.setFechaPedido(LocalDateTime.now());
        pedido.setFechaEntrega(LocalDateTime.now().plusDays(1)); // Entrega al día siguiente
        pedido.setEstado(true);
        pedido = pedidoRepository.save(pedido);

        // Crear los detalles del pedido
        for (Carrito item : itemsCarrito) {
            DetallePedido detalle = new DetallePedido();
            
            // Configurar la clave compuesta
            DetallePedidoId detalleId = new DetallePedidoId();
            detalleId.setIdPedido(pedido.getId());
            detalleId.setIdProducto(item.getProducto().getId());
            detalle.setId(detalleId);
            
            // Configurar las relaciones
            detalle.setPedido(pedido);
            detalle.setProducto(item.getProducto());
            
            // Configurar los demás campos
            detalle.setCantidad(item.getCantidad());
            detalle.setPrecio(item.getProducto().getPrecio());
            detalle.setSubtotal(item.getTotal());
            
            detallePedidoRepository.save(detalle);

            // Actualizar el stock del producto
            Producto producto = item.getProducto();
            producto.setStock(producto.getStock() - item.getCantidad());
        }

        // Vaciar el carrito
        carritoRepository.deleteAll(itemsCarrito);
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