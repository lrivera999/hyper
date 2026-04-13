com.hyper.hotel.model;

@Entity
@Table(name = "reservaXclientes")
public class ReservaCliente {

    @Id
    private Integer idReservaCliente;

    private Integer idReserva;
    private Integer idCliente;
    private String estado;
}