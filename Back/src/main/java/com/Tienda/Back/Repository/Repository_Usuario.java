package com.Tienda.Back.Repository;

import com.Tienda.Back.Modulos.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface Repository_Usuario extends JpaRepository<Usuario, Long> {
    boolean existsByNombre(String usernombre);

    boolean existsByEmail(String userEmail);

     Optional <Usuario> findByNombre(String nombre);
}
