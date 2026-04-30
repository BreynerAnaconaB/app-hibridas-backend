package com.Tienda.Back.Repository;

import com.Tienda.Back.Modulos.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface Repository_Usuario extends JpaRepository<Usuario, Long> {
    boolean existsByNombre(String nombre);
    boolean existsByEmail(String email);
    Optional<Usuario> findByNombre(String nombre);
    Optional<Usuario> findByEmail(String email);
}