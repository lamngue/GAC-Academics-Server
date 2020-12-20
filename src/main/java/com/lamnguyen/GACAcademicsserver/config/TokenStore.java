package com.lamnguyen.GACAcademicsserver.config;

import lombok.Data;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Component
@Data
public class TokenStore {

    private Map< String, Authentication > cache = new HashMap<>();
    private ArrayList<String> invalidatedTokens = new ArrayList<>();

    public String generateToken( Authentication authentication ) {
        String token = UUID.randomUUID().toString();
        cache.put( token, authentication );
        return token;
    }

    public void removeToken(String token) {
        cache.remove(token);
    }

    public ArrayList<String> addInvalidTokens(String token) {
        this.invalidatedTokens.add(token);
        return this.invalidatedTokens;
    }

    public Authentication getAuth( String token ) {
        return cache.getOrDefault( token, null );
    }
}
