package co.edu.unbosque.cine.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.io.Serial;
import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Table(name = "multiplex_funcion")
@Entity
public class Funcion implements Serializable {

    @Serial
    private static final long serialVersionUID = 48611L;

    public static final String ACTIVO = "A";
    public static final String INACTIVO = "I";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_multiplex_funcion")
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "fk_id_multiplex_pelicula", nullable = false)
    private MultiplexPelicula multiplexPelicula;

    @ManyToOne
    @JoinColumn(name = "fk_id_multiplex_sala", nullable = false)
    private MultiplexSala multiplexSala;

    @ManyToOne
    @JoinColumn(name = "fk_id_multiplex_tarifa_funcion", nullable = false)
    private MultiplexTarifa multiplexTarifa;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "fecha_inicio", nullable = false)
    private Date fechaInicio;

    @JsonIgnore
    @Column(name = "estado", nullable = false, length = 1)
    private String estado = ACTIVO;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Funcion funcion = (Funcion) o;
        return id != null && Objects.equals(id, funcion.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }

}
