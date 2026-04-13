package com.hyper.hotel.controller;

import com.hyper.hotel.security.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private JwtUtil jwt;

    @PostMapping("/login")
    public String login(@RequestBody Map<String, String> req) {

        if ("admin".equals(req.get("username")) &&
                "1234".equals(req.get("password"))) {

            return jwt.generarToken("admin");
        }

        throw new RuntimeException("Credenciales inválidas");
    }
}
