package com.xsty.security.controller;

import com.xsty.security.base.TokenUtil;
import com.xsty.security.domain.Login;
import com.xsty.security.domain.Token;
import com.xsty.security.model.Usuario;
import com.xsty.security.repository.UsuarioRepositorio;
import com.xsty.security.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by XST on 16/04/2016.
 */
@RestController
@RequestMapping(value = "${jwt.path}")
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private AuthService authService;

    @Autowired
    private UsuarioRepositorio usuarioRepositorio;

    @Autowired
    private TokenUtil tokenUtil;

    @RequestMapping(value = "${jwt.path.login}", method = RequestMethod.POST)
    public ResponseEntity<?> login(@RequestBody Login authenticationRequest) throws AuthenticationException {

        final Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        authenticationRequest.getNombre(),
                        authenticationRequest.getContrasena()
                )
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);

        final UserDetails userDetails = authService.loadUserByUsername(authenticationRequest.getNombre());
        final String token = tokenUtil.generateToken(userDetails);

        return ResponseEntity.ok(new Token(token));
    }


    @RequestMapping(value = "/whoami", method = RequestMethod.GET)
    public ResponseEntity<?> whoami() {
        String nombre = SecurityContextHolder.getContext().getAuthentication().getName();
        Usuario usuario = usuarioRepositorio.findByNombre(nombre);
        return ResponseEntity.ok(usuario);
    }

    @RequestMapping(value = "${jwt.path.signup}", method = RequestMethod.POST)
    public ResponseEntity<?> signup(@RequestBody Login authenticationRequest) {
        authService.createUser(authenticationRequest);

        Usuario usuario = usuarioRepositorio.findByNombre(authenticationRequest.getNombre());

        if (usuario != null) {
            return ResponseEntity.status(HttpStatus.CREATED).body(usuario);
        }

        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
    }
}
