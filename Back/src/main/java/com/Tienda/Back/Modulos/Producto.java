package com.Tienda.Back.Modulos;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Generated;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Producto {
@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long id_producto;
    private String nombre_producto;
    private String url_image_product;
    private String informacion_producto;
    private double precio_producto;
    private int stock_producto;
    private String categoria;


}
