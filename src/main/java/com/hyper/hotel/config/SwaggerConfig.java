@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI api() {
        return new OpenAPI()
                .info(new Info()
                        .title("API Hotel")
                        .version("1.0")
                        .description("Reservas Hotel"));
    }
}