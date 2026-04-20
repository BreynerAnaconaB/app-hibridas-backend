package com.Tienda.Back.Service;

import com.Tienda.Back.Modulos.Usuario;
import com.Tienda.Back.Repository.Repository_Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.AbstractValidatingPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
@Service
public class usuarioService {
    @Autowired
    private Repository_Usuario repositoryUsuario;

    @Autowired
    @Lazy
    private PasswordEncoder passwordEncoder;

    public Repository_Usuario getRepositoryUsuario() {
        return repositoryUsuario;
    }


    public List<Usuario> getList(){
        return repositoryUsuario.findAll();
    }

    public boolean UsuarioCrear (String Usernombre, String UserEmail, String Usercontraseña, String Usertelefono, LocalDateTime UserfechaRegistro ){
        if (repositoryUsuario.existsByNombre(Usernombre) || repositoryUsuario.existsByEmail(UserEmail)) {
            return false;
        }
        Usuario newUsuario = new Usuario();
        newUsuario.setNombre(Usernombre);
        newUsuario.setEmail(UserEmail);
        String hash = passwordEncoder.encode(Usercontraseña);
        newUsuario.setContraseña(hash);
        newUsuario.setTelefono(Usertelefono);
        newUsuario.setFechaRegistro(UserfechaRegistro);


       repositoryUsuario.save(newUsuario);
      return true;
    }

    public Object validacionUser(String Email, String contraseña, String nombre){

       return repositoryUsuario.findByNombre(nombre)
               .map(usuario -> passwordEncoder.matches(contraseña, usuario.getContraseña()))
               .orElse(false);
    }
}
