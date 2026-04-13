package com.Tienda.Back.Modulos;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Pedido {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long id_pedido;
    @OneToMany(mappedBy = "pedido", cascade = CascadeType.ALL)
    private Pedido pedido;
    @JoinColumn(name = "id_usuario")
    private int id_usuario;
    private LocalDateTime fecha_pedido;
    private double total_pedido;


}
