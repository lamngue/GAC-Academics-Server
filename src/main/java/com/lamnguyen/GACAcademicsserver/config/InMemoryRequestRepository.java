package com.lamnguyen.GACAcademicsserver.config;

import com.lamnguyen.GACAcademicsserver.api.ProfessorController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.oauth2.client.web.AuthorizationRequestRepository;
import org.springframework.security.oauth2.core.endpoint.OAuth2AuthorizationRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

public class InMemoryRequestRepository implements AuthorizationRequestRepository< OAuth2AuthorizationRequest > {

    private final Map< String, OAuth2AuthorizationRequest > cache = new HashMap<>();
    Logger logger = LoggerFactory.getLogger(ProfessorController.class);
    @Override
    public OAuth2AuthorizationRequest loadAuthorizationRequest( HttpServletRequest request ) {
        String state = request.getParameter( "state" );
        if ( state != null ) {
            return removeAuthorizationRequest( request );
        }
        return null;
    }

    @Override
    public void saveAuthorizationRequest( OAuth2AuthorizationRequest authorizationRequest,
                                          HttpServletRequest request, HttpServletResponse response ) {
        String state = authorizationRequest.getState();
        logger.info("State is " + state);
        cache.put( state, authorizationRequest );
    }

    @Override
    public OAuth2AuthorizationRequest removeAuthorizationRequest( HttpServletRequest request ) {
        String state = request.getParameter( "state" );
        if ( state != null ) {
            return cache.remove( state );
        }
        return null;
    }
}
