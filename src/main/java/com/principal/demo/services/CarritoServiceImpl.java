package com.principal.demo.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.principal.demo.model.Carrito;
import com.principal.demo.model.Producto;
import com.principal.demo.repository.CarritoRepository;
import com.principal.demo.repository.ProductoRepository;



@Service
public class CarritoServiceImpl implements CarritoService {

    @Autowired
    private ProductoRepository productoRepository;

    @Autowired
    private CarritoRepository carritoRepository;

    @Override
    public List<Producto> findAllProductos() {
        return productoRepository.findAll();
    }

    @Override
    public List<Producto> findAllProductosById(int id) {
        Optional<Producto> producto = productoRepository.findById(id);
        return producto.map(List::of).orElse(List.of());
    }


    @Override
    public List<Carrito> obtenerCarritosPorUsuario(int id) {
        return carritoRepository.findByUsuarioId(id);
    }


    @Override
    public void saveCarrito(int id,int idProducto,int cantidad,double total) {
        carritoRepository.saveCarritoByIdUsuario(id, idProducto,cantidad,total);
    }

    @Override
    public int actualizarProductoAgregado(int idUsuario, int idProducto, int cantidad, double subTotal){
        return carritoRepository.updateCantidadCarrito(idUsuario,idProducto,cantidad,subTotal);
    }
    
    @Override
    public int eliminarProductoAgregado(int idUsuario,int idProducto){
        return carritoRepository.deleteByUsuarioIdAndProductoId(idUsuario,idProducto);
    }

}
