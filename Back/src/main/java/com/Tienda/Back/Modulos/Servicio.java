package com.Tienda.Back.Modulos;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Servicio {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private long id_servicio;

    @ManyToOne
    @JoinColumn(name = "id_usuario", nullable = false)
    private Usuario usuario;

    private LocalDateTime fecha_servicio;

    @Column(length = 500)
    private String descripcion;
    private BigDecimal total_service;


    @OneToMany(mappedBy = "servicio", cascade = CascadeType.ALL)
    private List<Detalle_Servicio> detalleServicios;
}
