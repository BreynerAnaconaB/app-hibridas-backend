package com.Tienda.Back.Service;

import com.Tienda.Back.Modulos.Usuario;
import com.Tienda.Back.Repository.Repository_Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class usuarioService {

    @Autowired
    private Repository_Usuario repositoryUsuario;

    @Autowired
    @Lazy
    private PasswordEncoder passwordEncoder;

    /**
     * Crea un nuevo usuario validando que el nombre y email no existan previamente.
     */
    public boolean crearUsuario(String nombre, String email, String contraseña, String telefono, LocalDateTime fechaRegistro) {
        if (repositoryUsuario.existsByNombre(nombre) || repositoryUsuario.existsByEmail(email)) {
            return false;
        }

        Usuario newUsuario = new Usuario();
        newUsuario.setNombre(nombre);
        newUsuario.setEmail(email);

        // Encriptación de seguridad
        String hash = passwordEncoder.encode(contraseña);
        newUsuario.setContraseña(hash);

        newUsuario.setTelefono(telefono);
        newUsuario.setFechaRegistro(fechaRegistro);

        repositoryUsuario.save(newUsuario);
        return true;
    }

    /**
     * Obtiene la lista completa de usuarios.
     */
    public List<Usuario> obtenerTodos() {
        return repositoryUsuario.findAll();
    }

    /**
     * Busca un usuario por su ID único.
     */
    public Optional<Usuario> obtenerPorId(Long id) {
        return repositoryUsuario.findById(id);
    }

    /**
     * Actualiza los datos de un usuario existente.
     * Incluye validación para evitar duplicidad de email con otros usuarios.
     */
    public boolean actualizarUsuario(Long id, Usuario datosNuevos) {
        return repositoryUsuario.findById(id).map(user -> {

            // Validar si el nuevo email ya está en uso por otro ID
            if (!user.getEmail().equals(datosNuevos.getEmail()) &&
                    repositoryUsuario.existsByEmail(datosNuevos.getEmail())) {
                return false;
            }

            user.setNombre(datosNuevos.getNombre());
            user.setEmail(datosNuevos.getEmail());
            user.setTelefono(datosNuevos.getTelefono());


            if (datosNuevos.getContraseña() != null && !datosNuevos.getContraseña().isEmpty()) {
                user.setContraseña(passwordEncoder.encode(datosNuevos.getContraseña()));
            }

            repositoryUsuario.save(user);
            return true;
        }).orElse(false);
    }


    public boolean eliminarUsuario(Long id) {
        if (repositoryUsuario.existsById(id)) {
            repositoryUsuario.deleteById(id);
            return true;
        }
        return false;
    }


    public boolean validacionUser(String email, String contraseña) {
        return repositoryUsuario.findByEmail(email)
                .map(usuario -> passwordEncoder.matches(contraseña, usuario.getContraseña()))
                .orElse(false);
    }
}