package com.hyper.hotel.security;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.test.util.ReflectionTestUtils;

import static org.assertj.core.api.Assertions.*;

class JwtUtilTest {

    private JwtUtil jwtUtil;

    @BeforeEach
    void setUp() {
        jwtUtil = new JwtUtil();
        ReflectionTestUtils.setField(jwtUtil, "secret", "hyper-secret-key-must-be-at-least-32-bytes!");
        ReflectionTestUtils.setField(jwtUtil, "expiration", 3600000L);
    }

    @Test
    void generarToken_usuarioValido_retornaTokenNoVacio() {
        String token = jwtUtil.generarToken("admin");

        assertThat(token).isNotBlank();
    }

    @Test
    void extraerUsuario_tokenValido_retornaUsuarioCorrecto() {
        String token = jwtUtil.generarToken("admin");

        String usuario = jwtUtil.extraerUsuario(token);

        assertThat(usuario).isEqualTo("admin");
    }

    @Test
    void esValido_tokenValido_retornaTrue() {
        String token = jwtUtil.generarToken("admin");

        assertThat(jwtUtil.esValido(token)).isTrue();
    }

    @Test
    void esValido_tokenInvalido_retornaFalse() {
        assertThat(jwtUtil.esValido("token.invalido.xxx")).isFalse();
    }

    @Test
    void esValido_tokenVacio_retornaFalse() {
        assertThat(jwtUtil.esValido("")).isFalse();
    }
}
