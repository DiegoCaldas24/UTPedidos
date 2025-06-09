package com.example.Ejemplo.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.Ejemplo.models.Usuario;
import com.example.Ejemplo.repositories.UsuarioRepository;

import jakarta.servlet.http.HttpSession;

@Controller
public class LoginController {

    private final UsuarioRepository usuarioRepository;

    public LoginController(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    @GetMapping("/login")
    public String mostrarLogin() {
        return "login";
    }

    @PostMapping("/login")
    public String procesarLogin(
            @RequestParam String email,
            @RequestParam String password,
            HttpSession session,
            RedirectAttributes redirectAttributes) {
        
        Usuario usuario = usuarioRepository.findByCorreo(email);
        
        if (usuario != null && usuario.getPassword().equals(password)) {
            session.setAttribute("usuario", usuario);
            return "redirect:/inicio";
        }
        
        redirectAttributes.addFlashAttribute("error", "Credenciales inválidas");
        return "redirect:/login";
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/inicio";
    }
} 