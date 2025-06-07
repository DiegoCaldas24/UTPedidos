package com.avancecarrito.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import com.avancecarrito.demo.modelo.Categoria;
import com.avancecarrito.demo.modelo.Producto;


public interface ProductoRepository extends JpaRepository<Producto,Integer>{ 

    
    List<Producto> findByCategoria(Categoria categoria);
    
    List<Producto> findByNombreContaining(String nombre);
    
    List<Producto> findByEstadoTrue();

}
