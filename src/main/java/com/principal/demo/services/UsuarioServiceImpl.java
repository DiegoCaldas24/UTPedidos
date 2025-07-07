package com.principal.demo.services;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.principal.demo.model.Rol;
import com.principal.demo.model.Usuario;
import com.principal.demo.repository.UsuarioRepository;


@Service
public class UsuarioServiceImpl implements UsuarioService {
private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

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


    @Override
    public List<Usuario> findAllUsuariosByNotRol(Rol rol) {
        return usuarioRepository.findByRolNot(rol);
    }


  

    public void registrar(Usuario usuario) {
        usuario.setPassword(passwordEncoder.encode(usuario.getPassword()));
        usuario.setFechaIngreso(LocalDate.now().toString());
        usuarioRepository.save(usuario);
    }

    public Optional<Usuario> login(String correo, String rawPassword) {
        return usuarioRepository.findByCorreo(correo).filter(usuario -> usuario.isEstado() && passwordEncoder.matches(rawPassword, usuario.getPassword()));
    }
}
