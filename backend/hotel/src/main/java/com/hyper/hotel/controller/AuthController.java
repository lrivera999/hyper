package com.hyper.hotel.controller;

import com.hyper.hotel.security.JwtUtil;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/auth")
@Tag(name = "Autenticación", description = "Login y obtención de token JWT")
public class AuthController {

    private final JwtUtil jwt;

    public AuthController(JwtUtil jwt) {
        this.jwt = jwt;
    }

    @PostMapping("/login")
    @Operation(summary = "Iniciar sesión y obtener token JWT")
    public ResponseEntity<Map<String, String>> login(@RequestBody Map<String, String> req) {

        if ("admin".equals(req.get("username")) &&
                "1234".equals(req.get("password"))) {

            return ResponseEntity.ok(Map.of("token", jwt.generarToken("admin")));
        }

        return ResponseEntity.status(401).body(Map.of("mensaje", "Credenciales inválidas"));
    }
}
