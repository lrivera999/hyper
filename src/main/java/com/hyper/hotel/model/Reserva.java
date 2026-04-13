com.hyper.hotel.model;

@Entity
@Table(name = "reservas")
public class Reserva {

    @Id
    private Integer idReserva;

    private Integer numHuesped;
    private String fechaInicio;
    private String fechaFin;
    private String estado;
}