package com.lamnguyen.GACAcademicsserver.api;

import com.lamnguyen.GACAcademicsserver.config.TokenStore;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.Map;

@RestController
@RequestMapping( "/v1/home" )
public class HomeController {

    private final TokenStore tokenStore;
    Logger logger = LoggerFactory.getLogger(HomeController.class);
    @Autowired
    public HomeController(TokenStore tokenStore) {
        this.tokenStore = tokenStore;
    }

    @GetMapping
    public Map<String, String> getUserName( @AuthenticationPrincipal( expression = "attributes['name']" ) String username ) {
        return Collections.singletonMap("name", username);
    }
}
