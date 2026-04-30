package com.Tienda.Back.Controller;

import com.Tienda.Back.Modulos.Usuario;
import com.Tienda.Back.Service.usuarioService;
import com.Tienda.Back.DTO.LoginDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/user")
public class controllerUser {

    @Autowired
    private usuarioService usuarioService;

    // --- OBTENER TODOS (ADMIN) ---
    @GetMapping("/all")
    public ResponseEntity<List<Usuario>> mostrarUsuarios() {
        return ResponseEntity.ok(usuarioService.obtenerTodos());
    }

    // --- REGISTRO ---
    @PostMapping("/crear")
    public ResponseEntity<?> crearUsuario(@RequestBody Usuario datos) {
        boolean creado = usuarioService.crearUsuario(
                datos.getNombre(),
                datos.getEmail(),
                datos.getContraseña(),
                datos.getTelefono(),
                LocalDateTime.now()
        );

        if (!creado) {
            return ResponseEntity.status(HttpStatus.CONFLICT)
                    .body("Error: El nombre de usuario o email ya se encuentra registrado.");
        }
        return new ResponseEntity<>("Usuario registrado con éxito", HttpStatus.CREATED);
    }

    // --- LOGIN ---
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginDTO loginData) {
        boolean esValido = usuarioService.validacionUser(
                loginData.getEmail(),
                loginData.getContraseña()
        );

        if (esValido) {
            return ResponseEntity.ok("Login exitoso. ¡Bienvenido!");
        }

        return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                .body("Credenciales incorrectas. Por favor, verifica tu email y contraseña.");
    }

    // --- ACTUALIZAR ---
    @PutMapping("/actualizar/{id}")
    public ResponseEntity<?> actualizarUsuario(@PathVariable Long id, @RequestBody Usuario datosNuevos) {
        boolean actualizado = usuarioService.actualizarUsuario(id, datosNuevos);

        if (!actualizado) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("No se pudo actualizar: El ID no existe o el email ya está en uso por otro usuario.");
        }

        return ResponseEntity.ok("Datos actualizados correctamente.");
    }

    // --- ELIMINAR ---
    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<?> eliminarUsuario(@PathVariable Long id) {
        boolean eliminado = usuarioService.eliminarUsuario(id);

        if (!eliminado) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Error: No se encontró un usuario con el ID especificado.");
        }

        return ResponseEntity.ok("Usuario eliminado del sistema.");
    }
}