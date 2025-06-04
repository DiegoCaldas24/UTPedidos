package com.example.Ejemplo.controllers;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.Ejemplo.Services.ProductoService;
import com.example.Ejemplo.models.Categoria;
import com.example.Ejemplo.models.Producto;
import com.example.Ejemplo.repositories.CategoriaRepository;

@Controller
@RequestMapping("/productos")
public class AdminController {

    private final ProductoService productoService;
    private final CategoriaRepository categoriaRepository;

    @Autowired
    public AdminController(ProductoService productoService, CategoriaRepository categoriaRepository) {
        this.productoService = productoService;
        this.categoriaRepository = categoriaRepository;
    }

    @GetMapping 
    public String panelAdmin(Model model) {
        model.addAttribute("productos", productoService.findRecent());
        model.addAttribute("producto", new Producto());
        
        // Obtener todas las categorías de la base de datos
        List<Categoria> categorias = categoriaRepository.findAll();
        model.addAttribute("categorias", categorias);
        
        return "panelAdmin";
    }

    @PostMapping("/subirproductos")
    public String guardarProducto(@RequestParam("nombre") String nombre,
            @RequestParam("precio") BigDecimal precio,
            @RequestParam("descripcion") String descripcion,
            @RequestParam("categoria") String categoriaNombre,
            @RequestParam("imagen") MultipartFile imagen,
            @RequestParam(value = "disponible", defaultValue = "false") boolean disponible,
            @RequestParam(value = "id", required = false) Integer id,
            RedirectAttributes redirectAttributes) {

        Producto producto = new Producto();
        if (id != null) {
            producto.setId(id);
        }
        producto.setNombre(nombre);
        producto.setPrecio(precio);
        producto.setDescripcion(descripcion);
        
        // Buscar la categoría por nombre
        Categoria categoria = categoriaRepository.findByNombre(categoriaNombre);
        if (categoria == null) {
            categoria = new Categoria();
            categoria.setNombre(categoriaNombre);
            categoria = categoriaRepository.save(categoria);
        }
        producto.setCategoria(categoria);
        
        producto.setEstado(disponible);
        producto.setStock(100); // Valor por defecto

        productoService.save(producto, imagen);

        redirectAttributes.addFlashAttribute("mensaje", "Producto guardado correctamente");
        return "redirect:/productos";
    }

    @GetMapping("/{id}")
    public String editarProducto(@PathVariable Integer id, Model model) {
        Producto producto = productoService.findById(id);
        if (producto != null) {
            model.addAttribute("producto", producto);
            model.addAttribute("productos", productoService.findRecent());
            
            // Obtener todas las categorías de la base de datos
            List<Categoria> categorias = categoriaRepository.findAll();
            model.addAttribute("categorias", categorias);
            
            return "panelAdmin";
        }
        return "redirect:/productos";
    }

    @GetMapping("/eliminar/{id}")
    public String eliminarProducto(@PathVariable Integer id, RedirectAttributes redirectAttributes) {
        productoService.delete(id);
        redirectAttributes.addFlashAttribute("mensaje", "Producto eliminado correctamente");
        return "redirect:/productos";
    }
}
