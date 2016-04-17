package com.xsty.security.repository;

import com.xsty.security.model.Rol;
import com.xsty.security.model.RolTipo;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by XST on 16/04/2016.
 */
@Repository
public interface RolRespositorio extends PagingAndSortingRepository<Rol, Long> {
    Rol findByTipo(RolTipo tipo);
}
