com.hyper.hotel.model;

@Entity
@Table(name = "precios")
public class Precio {

    @Id
    private Integer idPrecio;

    private Integer idReserva;
    private Integer idHabitacion;
    private Integer esPromocion;
    private Integer esFeriado;
    private Integer tarifaAplicada;
    private String estado;
}