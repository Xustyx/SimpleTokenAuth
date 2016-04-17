package com.xsty.security.domain;

import java.io.Serializable;

/**
 * Created by XST on 16/04/2016.
 */

/***
 * Esta clase se usa para realizar el Login.
 */
public class Login implements Serializable {

    private static final long serialVersionUID = -1473888145632827577L;

    private String nombre;
    private String contrasena;

    public Login() {
        super();
    }

    public Login(String contrasena, String nombre) {
        this.contrasena = contrasena;
        this.nombre = nombre;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }
}
