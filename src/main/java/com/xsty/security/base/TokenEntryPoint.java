package com.xsty.security.base;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.Serializable;


/**
 * Created by XST on 16/04/2016.
 */
@Component
public class TokenEntryPoint implements AuthenticationEntryPoint, Serializable {

    private static final long serialVersionUID = -2093623152682088240L;

    @Override
    public void commence(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AuthenticationException e) throws IOException, ServletException {
        //Esto se ejecuta cuando se intenta acceder a un sitio sin token.
        httpServletResponse.sendError(HttpServletResponse.SC_UNAUTHORIZED, "No puedes hacer esto :(");
    }
}
