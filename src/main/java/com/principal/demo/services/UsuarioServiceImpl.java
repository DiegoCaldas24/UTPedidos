package com.principal.demo.services;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.principal.demo.model.Rol;
import com.principal.demo.model.Usuario;
import com.principal.demo.repository.UsuarioRepository;

@Service
public class UsuarioServiceImpl implements UsuarioService {

    private final UsuarioRepository usuarioRepository;

    @Autowired
    public UsuarioServiceImpl(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    @Override
    public List<Usuario> findAllUsuarios() {
        return usuarioRepository.findAll();
    }

    @Override
    public Optional<Usuario> findUsuarioById(Integer id) {
        return usuarioRepository.findById(id);
    }

    @Override
    public Optional<Usuario> findUsuarioPorNombre(String nombre) {
        return Optional.empty();
    }

    @Override
    public Usuario saveUser(Usuario usuario) {

        return usuarioRepository.save(usuario);
    }

    public void updateUsuario(Integer id) {

    }


    @Override
    public List<Usuario> findAllUsuariosByNotRol(Rol rol) {
        return usuarioRepository.findByRolNot(rol);
    }

}
