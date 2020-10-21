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

    @GetMapping
    public Map<String, Object> getUserName( @AuthenticationPrincipal( expression = "attributes" ) Object username ) {
        return Collections.singletonMap("name", username);
    }
}
