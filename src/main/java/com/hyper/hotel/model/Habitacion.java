com.hyper.hotel.model.Habitacion;

@Entity
@Table(name = "habitaciones")
public class Habitacion {

    @Id
    private Integer idHabitacion;

    private Integer piso;
    private Integer habitacion;
    private String menaje;
    private String estado;
}
