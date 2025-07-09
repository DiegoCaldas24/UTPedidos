package com.principal.demo.services;

import com.principal.demo.model.Pedido;

import java.util.List;

public interface PedidoService {
    void guardarPedido(Pedido pedido);
    List<Pedido> findByUsuario_Id(int usuarioId);
    String generarCodigoUnico();
    List<String> obtenerTodosLosCodigos();

}
