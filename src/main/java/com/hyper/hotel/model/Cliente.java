com.hyper.hotel.model;

@Entity
@Table(name = "clientes")
public class Cliente {

    @Id
    private Integer idCliente;

    private String nombres;
    private String apellidos;
    private Integer edad;
    private String estado;
}