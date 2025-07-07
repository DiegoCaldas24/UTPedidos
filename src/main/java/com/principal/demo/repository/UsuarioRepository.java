package com.principal.demo.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.principal.demo.model.Rol;
import com.principal.demo.model.Usuario;
//proporciona metodos crud para usuario 
@Repository
public interface UsuarioRepository extends JpaRepository<Usuario,Integer> {
    List<Usuario> findByRolNot(Rol rol);
    Optional<Usuario> findByCorreo(String correo);
}
