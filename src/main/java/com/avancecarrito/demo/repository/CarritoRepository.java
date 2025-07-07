package com.avancecarrito.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
<<<<<<< Updated upstream:src/main/java/com/avancecarrito/demo/repository/CarritoRepository.java
import java.util.List;
import com.avancecarrito.demo.modelo.Carrito;
@Repository 
=======

import com.principal.demo.model.Carrito;

@Repository
>>>>>>> Stashed changes:src/main/java/com/principal/demo/repository/CarritoRepository.java
public interface CarritoRepository extends JpaRepository<Carrito,Integer>{

    List<Carrito> findByUsuarioId(Integer idUsuario);
    
}
