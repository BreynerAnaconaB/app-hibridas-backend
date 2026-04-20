package com.Tienda.Back.Controller;

import com.Tienda.Back.Modulos.Usuario;
import com.Tienda.Back.Service.usuarioService;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@Controller
@RestController
public class controllerUser {
    @Autowired
    private usuarioService usuarioService;

    @GetMapping("/Admin")
    public ResponseEntity<List<Usuario>> MostrarUsuarios(){
      return ResponseEntity.ok(usuarioService.getList());
    }

    @PostMapping("/user/crear")
    public ResponseEntity<?> CrearUsuario(@RequestBody Usuario Datos){
        boolean CrearUser = usuarioService.UsuarioCrear(
               Datos.getNombre(),
                Datos.getEmail(),
                Datos.getContraseña(),
                Datos.getTelefono(),
                LocalDateTime.now()
                );
        if (CrearUser){
            return ResponseEntity.status(HttpStatus.CONFLICT).body("El usuario ya esta creado");
        }
        return new ResponseEntity<>("Usuario creado con exito", HttpStatus.CREATED);
    }
}
