package com.example.Ejemplo.Services;

import java.math.BigDecimal;
import java.util.List;

import com.example.Ejemplo.models.Carrito;
import com.example.Ejemplo.models.Producto;
import com.example.Ejemplo.models.Usuario;

public interface CarritoService {
    List<Carrito> obtenerCarritoPorUsuario(Integer idUsuario);
    void vaciarCarritoPorUsuario(Integer idUsuario);
    void agregarAlCarrito(Carrito carrito);
    void eliminarDelCarrito(Integer idCarrito);
    BigDecimal calcularTotal(Carrito carrito);
    void agregarProducto(Integer usuarioId, Producto producto, int cantidad);
    void confirmarPedido(Integer usuarioId);
}
