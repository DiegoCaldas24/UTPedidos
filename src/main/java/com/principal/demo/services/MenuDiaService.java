package com.principal.demo.services;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.principal.demo.model.MenuDia;
import com.principal.demo.model.Producto;

public interface MenuDiaService {
    List<MenuDia> findAllMenuDias();
    MenuDia saveMenudia(MenuDia menu);
    Page<Producto> obtenerProductosDelMenuDeHoyPorCategoria(String categoria, Pageable pageable);
}
