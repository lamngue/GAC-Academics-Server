package com.lamnguyen.GACAcademicsserver.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collections;

@Component
public class TokenFilter extends OncePerRequestFilter {

    private final TokenStore tokenStore;
    private final ObjectMapper mapper;

    public TokenFilter(TokenStore tokenStore, ObjectMapper mapper) {
        this.tokenStore = tokenStore;
        this.mapper = mapper;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String authToken = request.getHeader("Authorization");
        if (authToken != null) {
            String token = authToken.split(" ")[1];
            if (tokenStore.getInvalidatedTokens().contains(token)) {
                response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                response.getWriter().write( mapper.writeValueAsString( Collections.singletonMap( "error", "Token expired" ) ) );
                return;
            }
            Authentication authentication = tokenStore.getAuth(token);
            if (authentication != null) {
                SecurityContextHolder.getContext().setAuthentication(authentication);
            }
        }
        filterChain.doFilter(request,response);
    }
}
