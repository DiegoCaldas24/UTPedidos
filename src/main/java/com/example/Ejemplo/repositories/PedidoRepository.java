package com.example.Ejemplo.repositories;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.Ejemplo.models.Pedido;
import com.example.Ejemplo.models.Usuario;

@Repository
public interface PedidoRepository extends JpaRepository<Pedido, Integer> {
    
    List<Pedido> findByUsuario(Usuario usuario);
    
    List<Pedido> findByFechaPedidoBetween(LocalDateTime inicio, LocalDateTime fin);
    
    List<Pedido> findByEstado(Boolean estado);
}