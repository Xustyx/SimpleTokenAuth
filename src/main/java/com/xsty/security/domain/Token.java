package com.xsty.security.domain;

import java.io.Serializable;

/**
 * Created by XST on 16/04/2016.
 */
public class Token implements Serializable {

    private static final long serialVersionUID = -7535165423427760650L;

    private final String token;

    public Token(String token) {
        this.token = token;
    }

    public String getToken() {
        return token;
    }
}
