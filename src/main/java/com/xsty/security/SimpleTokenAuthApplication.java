package com.xsty.security;

import com.xsty.security.model.Rol;
import com.xsty.security.model.RolTipo;
import com.xsty.security.model.Usuario;
import com.xsty.security.repository.RolRespositorio;
import com.xsty.security.repository.UsuarioRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.Arrays;

@SpringBootApplication
public class SimpleTokenAuthApplication {

    @Autowired
    private RolRespositorio rolRespositorio;

    @Autowired
    private UsuarioRepositorio usuarioRepositorio;

    public static void main(String[] args) {
        SpringApplication.run(SimpleTokenAuthApplication.class, args);
    }

    @PostConstruct
    void init() {
        rolRespositorio.deleteAll();

        Rol rolUsuario = new Rol(RolTipo.USUARIO);
        Rol rolAdmin = new Rol(RolTipo.ADMIN);

        rolUsuario = rolRespositorio.save(rolUsuario);
        rolAdmin = rolRespositorio.save(rolAdmin);

        Usuario usuarioUsuario = new Usuario();
        Usuario usuarioAdmin = new Usuario();

        usuarioUsuario.setNombre("usuario");
        usuarioUsuario.setContrasena("usuario");
        usuarioUsuario.setRoles(new ArrayList<Rol>(Arrays.asList(rolUsuario)));

        usuarioAdmin.setNombre("admin");
        usuarioAdmin.setContrasena("1234");
        usuarioAdmin.setRoles(new ArrayList<Rol>(Arrays.asList(rolAdmin)));

        usuarioRepositorio.save(usuarioUsuario);
        usuarioRepositorio.save(usuarioAdmin);
    }
}
