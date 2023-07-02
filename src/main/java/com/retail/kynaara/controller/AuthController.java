package com.retail.kynaara.controller;

import com.retail.kynaara.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.annotation.RequestScope;

import java.util.Map;

@Controller
@RequestMapping(value = "api/v1/auth")
public class AuthController {

    @Autowired
    AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<Object> login(@RequestBody Map<String, String> requestMap){
        return authService.login(requestMap);
    }
}
