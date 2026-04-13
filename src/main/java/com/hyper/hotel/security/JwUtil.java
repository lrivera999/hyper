com.hyper.hotel.security;

@Component
public class JwtUtil {

    @Value("${jwt.secret}")
    private String secret;

    public String generarToken(String user) {
        return Jwts.builder()
                .setSubject(user)
                .signWith(SignatureAlgorithm.HS512, secret)
                .compact();
    }
}