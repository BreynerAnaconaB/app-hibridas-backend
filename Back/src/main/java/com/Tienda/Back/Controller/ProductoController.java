package com.Tienda.Back.Controller;

import com.Tienda.Back.Modulos.Producto;
import com.Tienda.Back.Service.ProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/productos")
public class ProductoController {

    @Autowired
    private ProductoService productoService;

    // --- OBTENER TODOS ---
    @GetMapping("/all")
    public ResponseEntity<List<Producto>> listarTodos() {
        List<Producto> productos = productoService.obtenerTodos();
        return ResponseEntity.ok(productos);
    }

    // --- FILTRAR POR CATEGORÍA ---
    @GetMapping("/categoria/{nombre}")
    public ResponseEntity<List<Producto>> filtrarPorCategoria(@PathVariable String nombre) {
        List<Producto> filtrados = productoService.filtrarPorCategoria(nombre);
        return ResponseEntity.ok(filtrados);
    }

    // --- CREAR PRODUCTO ---
    @PostMapping("/crear")
    public ResponseEntity<Producto> crear(@RequestBody Producto producto) {
        Producto nuevoProducto = productoService.crearProducto(producto);
        return new ResponseEntity<>(nuevoProducto, HttpStatus.CREATED);
    }

    // --- ACTUALIZAR PRODUCTO ---
    @PutMapping("/actualizar/{id}")
    public ResponseEntity<?> actualizar(@PathVariable Long id, @RequestBody Producto detalles) {
        return productoService.actualizarProducto(id, detalles)
                .map(producto -> ResponseEntity.ok("Producto actualizado con éxito"))
                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body("No se encontró el producto con ID: " + id));
    }

    // --- ELIMINAR PRODUCTO ---
    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<?> eliminar(@PathVariable Long id) {
        boolean eliminado = productoService.eliminarProducto(id);

        if (eliminado) {
            return ResponseEntity.ok("Producto eliminado correctamente");
        }

        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body("Error: El producto con ID " + id + " no existe.");
    }
}