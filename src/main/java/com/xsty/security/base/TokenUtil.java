package com.xsty.security.base;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by XST on 16/04/2016.
 */
@Component
public class TokenUtil implements Serializable {

    private static final long serialVersionUID = -1842290942597893855L;

    //Esto son las key de objetos que podemos meter en el claim del token.
    //Para el nombre se usa subject. Aquí uso un claim de ejemplo.
    private static final String CLAIM_KEY_NOMBRE = "nombre";

    @Value("${jwt.secreto}")
    private String secreto;

    @Value("${jwt.expiracion}")
    private Long expiracion;

    public String getNombreFromToken(String token) {
        String nombre;
        try {
            final Claims claims = getClaimsFromToken(token);
            nombre = claims.get(CLAIM_KEY_NOMBRE, String.class);
        } catch (Exception e) {
            nombre = null;
        }
        return nombre;
    }

    public Date getExpirationDateFromToken(String token) {
        Date expiracion;
        try {
            final Claims claims = getClaimsFromToken(token);
            expiracion = claims.getExpiration();
        } catch (Exception e) {
            expiracion = null;
        }
        return expiracion;
    }

    public String generateToken(UserDetails userDetails) {
        Map<String, Object> claims = new HashMap<>();
        claims.put(CLAIM_KEY_NOMBRE, userDetails.getUsername());
        return generateToken(claims);
    }

    private String generateToken(Map<String, Object> claims) {
        return Jwts.builder()
                .setClaims(claims)
                .setExpiration(generateExpirationDate())
                .signWith(SignatureAlgorithm.HS512, this.secreto)
                .compact();
    }


    public Boolean validarToken(String token, UserDetails userDetails) {
        final String nombre = getNombreFromToken(token);
        return (
                nombre.equals(userDetails.getUsername())
                        && !isTokenExpired(token));
    }

    private Date generateExpirationDate() {
        return new Date(System.currentTimeMillis() + this.expiracion * 1000);
    }


    private Claims getClaimsFromToken(String token) {
        Claims claims;
        try {
            claims = Jwts.parser()
                    .setSigningKey(secreto)
                    .parseClaimsJws(token)
                    .getBody();
        } catch (Exception e) {
            claims = null;
        }
        return claims;
    }

    private Boolean isTokenExpired(String token) {
        final Date expiracion = getExpirationDateFromToken(token);
        return expiracion.before(new Date());
    }

}
