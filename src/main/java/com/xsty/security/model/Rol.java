package com.xsty.security.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

/**
 * Created by XST on 16/04/2016.
 */
@Entity
public class Rol {
    private Long id;
    private RolTipo tipo;
    //private List<Usuario> usuarios;

    public Rol() {
    }

    public Rol(RolTipo tipo) {
        this.tipo = tipo;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @NotNull
    @Enumerated(EnumType.STRING)
    public RolTipo getTipo() {
        return tipo;
    }

    public void setTipo(RolTipo tipo) {
        this.tipo = tipo;
    }

//    @ManyToMany(mappedBy = "roles")
//    public List<Usuario> getUsuarios() {
//        return usuarios;
//    }
//
//    public void setUsuarios(List<Usuario> usuarios) {
//        this.usuarios = usuarios;
//    }

}
