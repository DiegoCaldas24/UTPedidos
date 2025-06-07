package com.example.Ejemplo.controllers;

import java.math.BigDecimal;
import java.util.List;

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

    public AdminController(ProductoService productoService, CategoriaRepository categoriaRepository) {
        this.productoService = productoService;
        this.categoriaRepository = categoriaRepository;
    }

    @GetMapping
    public String panelAdmin(Model model) {
        List<Producto> productos = productoService.findAll();
        if (productos.isEmpty()) {
            System.out.println("No se encontraron productos en la base de datos.");
        } else {
            System.out.println("Productos encontrados: " + productos.size());
            for (Producto p : productos) {
                System.out.println("Producto: " + p.getNombre() + ", Categoria: " + (p.getCategoria() != null ? p.getCategoria().getNombre() : "null"));
            }
        }
        model.addAttribute("productos", productos);
        model.addAttribute("producto", new Producto());
        List<Categoria> categorias = categoriaRepository.findAll();
        model.addAttribute("categorias", categorias);
        return "panelAdmin";
    }

    @PostMapping("/subirproductos")
    public String guardarProducto(
            @RequestParam("nombre") String nombre,
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
        Categoria categoria = categoriaRepository.findByNombre(categoriaNombre);
        if (categoria == null) {
            categoria = new Categoria();
            categoria.setNombre(categoriaNombre);
            categoria = categoriaRepository.save(categoria);
        }
        producto.setCategoria(categoria);
        producto.setEstado(disponible);
        producto.setStock(100);
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
