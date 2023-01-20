/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.legal.legal.webservice;

import java.util.Date;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import io.jsonwebtoken.Claims;

/**
 *
 * @author tsotsoa
 */
public class TokenHandler {

    public static final long DateEXP =1000000000;
    public static final String keyToken = "qazwsxedcrfvtgbyhnujmik";

    int id;
    String token;
    Date dateexpiration;
    int utilisateur;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Date getDateexpiration() {
        return dateexpiration;
    }

    public void setDateexpiration(Date dateexpiration) {
        this.dateexpiration = dateexpiration;
    }

    public int getUtilisateur() {
        return utilisateur;
    }

    public void setUtilisateur(int utilisateur) {
        this.utilisateur = utilisateur;

    }

    public String CreerToken(int utilisateurid) {
        long now = System.currentTimeMillis();
        Date dt = new Date(now + TokenHandler.DateEXP);
        String token = Jwts.builder().signWith(SignatureAlgorithm.HS256, TokenHandler.keyToken)
                .setIssuedAt(new Date(now))
                .setExpiration(dt)
                .claim("idutilisateur", utilisateurid)
                .compact();
        return token;
    }

    public TokenHandler ReturnToken(int idutilisateur) {
        String token = new TokenHandler().CreerToken(idutilisateur);
        Claims cl = Jwts.parser().setSigningKey(TokenHandler.keyToken)
                .parseClaimsJws(token).getBody();
        TokenHandler tok = new TokenHandler();
        tok.setToken(token);
        tok.setDateexpiration(cl.getExpiration());
        tok.setUtilisateur(idutilisateur);
        return tok;

    }

    public Date getDateEXP(String token) {
        Claims cl = Jwts.parser().setSigningKey(TokenHandler.keyToken)
                .parseClaimsJws(token).getBody();
        return cl.getExpiration();
    }

    public TokenHandler ToToken(String tok) throws Exception {
        TokenHandler token = new TokenHandler();
        try {
            Claims cl = Jwts.parser().setSigningKey(TokenHandler.keyToken)
                    .parseClaimsJws(tok).getBody();
            int idutilisateur = Integer.parseInt(cl.get("idutilisateur").toString());
            token.setUtilisateur(idutilisateur);
            token.setToken(tok);
        } catch (Exception f) {
            throw new Exception("loginerror");
        }
        return token;
    }

}
