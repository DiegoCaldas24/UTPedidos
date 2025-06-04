package com.example.Ejemplo.repositories;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.Ejemplo.models.MenuDia;

@Repository
public interface MenuDiaRepository extends JpaRepository<MenuDia, Integer> {
    
    List<MenuDia> findByFechaBetween(LocalDateTime inicio, LocalDateTime fin);
}