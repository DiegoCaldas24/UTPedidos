package com.avancecarrito.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.avancecarrito.demo.modelo.Productos;

public interface ProductoRepository extends JpaRepository<Productos,Integer>{ 

}
