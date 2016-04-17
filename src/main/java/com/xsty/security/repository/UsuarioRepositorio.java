package com.xsty.security.repository;

import com.xsty.security.model.Usuario;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 * Created by XST on 16/04/2016.
 */
@Repository
public interface UsuarioRepositorio extends PagingAndSortingRepository<Usuario, Long> {
    Usuario findByNombre(@Param("nombre") String nombre);
}
