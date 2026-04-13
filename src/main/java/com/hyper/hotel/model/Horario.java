com.hyper.hotel.model;

@Entity
@Table(name = "horarios")
public class Horario {

    @Id
    private Integer idHorario;

    private Integer idHabitacion;
    private String horaIngreso;
    private String horaSalida;
    private String estado;
}