package com.principal.demo.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.principal.demo.model.MenuDia;
import com.principal.demo.model.Producto;
import com.principal.demo.repository.MenuDiaRepository;

@Service
public class MenuDiaServiceImpl implements MenuDiaService {

    private final MenuDiaRepository menuDiaRepository;

    @Autowired
    public MenuDiaServiceImpl(MenuDiaRepository menuDiaRepository) {
        this.menuDiaRepository = menuDiaRepository;
    }

    @Override
    public List<MenuDia> findAllMenuDias() {
        return menuDiaRepository.findAll();
    }

    @Override
    public MenuDia saveMenudia(MenuDia menu) {
        return menuDiaRepository.save(menu);
    }
    @Override
    public Page<Producto> obtenerProductosDelMenuDeHoyPorCategoria(String categoria, Pageable pageable) {
        return menuDiaRepository.obtenerProductosDelMenuDeHoyPorCategoria(categoria, pageable);
    }
}
