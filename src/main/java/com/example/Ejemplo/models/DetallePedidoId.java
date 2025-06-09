package com.example.Ejemplo.models;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Data;

@Data
@Embeddable
public class DetallePedidoId implements Serializable {
    
    @Column(name = "id_pedido")
    private Integer idPedido;
    
    @Column(name = "id_producto")
    private Integer idProducto;
}