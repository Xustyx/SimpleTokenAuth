package com.xsty.security.service;

import com.xsty.security.domain.Login;
import com.xsty.security.domain.UsuarioDetalle;
import com.xsty.security.model.Rol;
import com.xsty.security.model.RolTipo;
import com.xsty.security.model.Usuario;
import com.xsty.security.repository.RolRespositorio;
import com.xsty.security.repository.UsuarioRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

/**
 * Created by XST on 16/04/2016.
 */
@Service
public class AuthService implements UserDetailsService {

    @Autowired
    private UsuarioRepositorio usuarioRepositorio;

    @Autowired
    private RolRespositorio rolRespositorio;

    @Override
    public UserDetails loadUserByUsername(String nombre) throws UsernameNotFoundException {
        Usuario usuario = usuarioRepositorio.findByNombre(nombre);

        if (usuario == null) {
            throw new UsernameNotFoundException("No se ha encontrado un usuario con ese nombre");
        } else {
            UsuarioDetalle usuarioDetalle = new UsuarioDetalle();
            usuarioDetalle.setNombre(usuario.getNombre());
            usuarioDetalle.setContrasena(usuario.getContrasena());
            usuarioDetalle.setRoles(usuario.getRoles());

            return usuarioDetalle;
        }
    }

    public Usuario createUser(Login login) {
        Usuario usuario = usuarioRepositorio.findByNombre(login.getNombre());

        if (usuario == null) {
            Rol rol = rolRespositorio.findByTipo(RolTipo.USUARIO);
            usuario = new Usuario();

            usuario.setNombre(login.getNombre());
            usuario.setContrasena(login.getContrasena());

            if (usuario.getRoles() == null) {
                usuario.setRoles(new ArrayList<Rol>());
            }

            usuario.getRoles().add(rol);

            usuarioRepositorio.save(usuario);
        }

        return null;
    }

}
