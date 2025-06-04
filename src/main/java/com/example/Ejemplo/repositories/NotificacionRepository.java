package com.example.Ejemplo.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.Ejemplo.models.Notificacion;
import com.example.Ejemplo.models.Usuario;

@Repository
public interface NotificacionRepository extends JpaRepository<Notificacion, Integer> {
    
    List<Notificacion> findByUsuario(Usuario usuario);
    
    List<Notificacion> findByUsuarioAndEstado(Usuario usuario, Boolean estado);
}