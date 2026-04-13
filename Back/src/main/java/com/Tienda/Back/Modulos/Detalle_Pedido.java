package com.Tienda.Back.Modulos;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Detalle_Pedido {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id_detalle_pedido;
    @JoinColumn(name = "id_pedido")
    private long id_pedido;
}
