package com.Tienda.Back.Service;

import com.Tienda.Back.Modulos.Producto;
import com.Tienda.Back.Repository.ProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductoService {

    @Autowired
    private ProductoRepository productoRepository;

    /**
     * Obtiene todos los productos disponibles en la base de datos.
     */
    public List<Producto> obtenerTodos() {
        return productoRepository.findAll();
    }

    /**
     * Filtra productos por una categoría específica.
     */
    public List<Producto> filtrarPorCategoria(String categoria) {
        return productoRepository.findByCategoria(categoria);
    }

    /**
     * Crea un nuevo producto.
     * Al usar URLs para las imágenes, el rendimiento es óptimo.
     */
    public Producto crearProducto(Producto producto) {
        return productoRepository.save(producto);
    }

    /**
     * Actualiza un producto existente.
     * Retorna un Optional para que el Controller decida cómo manejar el error si no existe.
     */
    public Optional<Producto> actualizarProducto(Long id, Producto detalles) {
        return productoRepository.findById(id).map(producto -> {
            producto.setNombre_producto(detalles.getNombre_producto());
            producto.setUrl_image_product(detalles.getUrl_image_product());
            producto.setInformacion_producto(detalles.getInformacion_producto());
            producto.setPrecio_producto(detalles.getPrecio_producto());
            producto.setStock_producto(detalles.getStock_producto());
            producto.setCategoria(detalles.getCategoria());
            return productoRepository.save(producto);
        });
    }

    /**
     * Elimina un producto por su ID.
     */
    public boolean eliminarProducto(Long id) {
        if (productoRepository.existsById(id)) {
            productoRepository.deleteById(id);
            return true;
        }
        return false;
    }
}