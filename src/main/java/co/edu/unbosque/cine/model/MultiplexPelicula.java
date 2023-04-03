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
import java.util.Objects;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Table(name = "multiplex_pelicula")
@Entity
public class MultiplexPelicula implements Serializable {

    @Serial
    private static final long serialVersionUID = 2444130L;

    public static final String ACTIVO = "A";
    public static final String INACTIVO = "I";
    public static final String EN_CARTELERA = "C";
    public static final String PROXIMAMENTE = "P";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_multiplex_pelicula")
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "fk_id_pelicula", nullable = false)
    private Pelicula pelicula;

    @ManyToOne
    @JoinColumn(name = "fk_id_multiplex", nullable = false)
    private Multiplex multiplex;

    @JsonIgnore
    @Column(name = "estado", nullable = false, length = 1)
    private String estado = ACTIVO;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        MultiplexPelicula multiplexPelicula = (MultiplexPelicula) o;
        return id != null && Objects.equals(id, multiplexPelicula.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
