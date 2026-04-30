package com.Tienda.Back.Repository;

import com.Tienda.Back.Modulos.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface Repository_Usuario extends JpaRepository<Usuario, Long> {

    // Verificaciones para el registro
    boolean existsByNombre(String nombre);
    boolean existsByEmail(String email);

    // Búsqueda para el login o perfiles
    Optional<Usuario> findByNombre(String nombre);

    // Agregamos este para que el login por email funcione
    Optional<Usuario> findByEmail(String email);
}