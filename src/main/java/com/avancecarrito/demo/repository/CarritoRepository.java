package com.avancecarrito.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import com.avancecarrito.demo.modelo.Carrito;
import com.avancecarrito.demo.modelo.Usuario;
@Repository 
public interface CarritoRepository extends JpaRepository<Carrito,Integer>{

    List<Carrito> findByUsuarioId(Integer idUsuario);
    
}
