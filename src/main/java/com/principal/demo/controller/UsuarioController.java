package com.principal.demo.controller;

import org.springframework.web.bind.annotation.GetMapping;

public class UsuarioController{
    @GetMapping("/usuarios")
    public String usuarios(){
        return "";
    }
}