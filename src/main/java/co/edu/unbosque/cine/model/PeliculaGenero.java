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
@Entity
@Table(name = "pelicula_genero")
public class PeliculaGenero implements Serializable {

    @Serial
    private static final long serialVersionUID = 12530L;

    public static final String ACTIVO = "A";
    public static final String INACTIVO = "I";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_pelicula_genero")
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "fk_id_pelicula", nullable = false)
    private Pelicula pelicula;

    @ManyToOne
    @JoinColumn(name = "fk_id_genero", nullable = false)
    private GeneroPelicula genero;

    @JsonIgnore
    @Column(name = "estado", nullable = false, length = 1)
    private String estado = ACTIVO;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        PeliculaGenero peliculaGenero = (PeliculaGenero) o;
        return id != null && Objects.equals(id, peliculaGenero.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
