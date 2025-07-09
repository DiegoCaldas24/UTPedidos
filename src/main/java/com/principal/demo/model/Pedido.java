package com.principal.demo.model;

import java.time.LocalDateTime;
import java.time.LocalTime;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "pedidos")
public class Pedido {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idPedido;

    @ManyToOne
    @JoinColumn(name = "id_usuario")
    private Usuario usuario;

    @Column(name = "fecha_pedido")
    private LocalDateTime fechaPedido;
    @Column(name = "hora_entrega")
    private LocalTime fechaEntrega;
    @Column(name = "estado")
    private boolean estado;

    @Column(name = "codigo_aleatorio")
    private String codigo;

    @PrePersist
    protected void onCreate() {
        fechaPedido = LocalDateTime.now();
    }
}

