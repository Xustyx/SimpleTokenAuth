package com.xsty.security.domain;

import com.xsty.security.model.Rol;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by XST on 16/04/2016.
 */
public class UsuarioDetalle implements UserDetails {

    private String nombre;
    private String contrasena;
    private List<GrantedAuthority> roles;

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    public void setRoles(List<Rol> roles) {
        this.roles = mapToGrantedAuthorities(roles);
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return roles;
    }

    @Override
    public String getPassword() {
        return contrasena;
    }

    @Override
    public String getUsername() {
        return nombre;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    private List<GrantedAuthority> mapToGrantedAuthorities(List<Rol> roles) {
        return roles.stream()
                .map(rol -> new SimpleGrantedAuthority(rol.getTipo().name()))
                .collect(Collectors.toList());
    }
}
