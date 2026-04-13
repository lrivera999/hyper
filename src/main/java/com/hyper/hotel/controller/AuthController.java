com.hyper.hotel.controller;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private JwtUtil jwt;

    @PostMapping("/login")
    public String login(@RequestBody Map<String,String> req) {

        if ("admin".equals(req.get("username")) &&
                "1234".equals(req.get("password"))) {

            return jwt.generarToken("admin");
        }

        throw new RuntimeException("Credenciales inválidas");
    }
}