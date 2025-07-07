package com.principal.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.principal.demo.model.Rol;
import com.principal.demo.model.Usuario;
import com.principal.demo.services.UsuarioServiceImpl;

import jakarta.servlet.http.HttpSession;

@Controller
public class UsuarioController {

    @Autowired
    private UsuarioServiceImpl usuarioServiceImpl;

    @GetMapping("/")
    public String redireccionRaiz() {
        return "loguin";
    }

    @PostMapping("/loguin")
    public String login(@RequestParam String username,
            @RequestParam String password,
            Model model, HttpSession session) {
        return usuarioServiceImpl.login(username, password)
                .map(usuario -> {
                    session.setAttribute("usuario", usuario);
                    return "redirect:/catalogo";
                })
                .orElseGet(() -> {
                    model.addAttribute("error", "Credenciales inválidas o usuario inactivo.");
                    return "loguin";
                });
    }

    @GetMapping("/register")
    public String mostrarRegistro(Model model) {
        model.addAttribute("usuario", new Usuario());
        return "register";
    }

    @PostMapping("/register")
    public String registrar(@ModelAttribute Usuario usuario, Model model) {
        try {
            usuario.setRol(Rol.USUARIO);
            usuario.setEstado(true);
            usuarioServiceImpl.registrar(usuario);
            return "redirect:/loguin";
        } catch (Exception e) {
            e.printStackTrace();
            String mensaje = e.getMessage().toLowerCase();
            if (mensaje.contains("correo") && mensaje.contains("duplicate")) {
                model.addAttribute("error", "El correo ya está registrado.");
            } else {
                model.addAttribute("error", "Ocurrió un error durante el registro.");
            }
            return "register";
        }
    }

}
