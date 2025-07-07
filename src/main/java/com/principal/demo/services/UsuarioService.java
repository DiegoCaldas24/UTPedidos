package com.principal.demo.services;
import java.util.List;
import java.util.Optional;

import com.principal.demo.model.Rol;
import com.principal.demo.model.Usuario;

public interface UsuarioService {
    List<Usuario> findAllUsuarios();
    Optional<Usuario> findUsuarioById(Integer id);
    Optional<Usuario> findUsuarioPorNombre(String nombre);
    Usuario saveUser(Usuario usuario);
    List<Usuario> findAllUsuariosByNotRol(Rol rol);
}
