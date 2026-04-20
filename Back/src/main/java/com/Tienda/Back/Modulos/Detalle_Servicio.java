package com.Tienda.Back.Modulos;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Detalle_Servicio {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_detalle_servicio;

    @ManyToOne
    @JoinColumn(name = "id_service", nullable = false)
    private Servicio servicio;

    @Column(name = "descripcion_item")
    private String descriptionItem;

    private Integer cantidad;

    @Column(name = "precio_unitario")
    private BigDecimal precio_unitario;

    private BigDecimal subtotal;
}
