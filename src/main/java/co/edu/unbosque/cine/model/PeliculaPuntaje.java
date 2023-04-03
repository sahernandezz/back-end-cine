package co.edu.unbosque.cine.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
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
@Table(name = "multiplex_pelicula_puntaje")
public class PeliculaPuntaje implements Serializable {

    @Serial
    private static final long serialVersionUID = 92430L;

    public static final String ACTIVO = "A";
    public static final String INACTIVO = "I";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_multiplex_pelicula_puntaje")
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "fk_id_pelicula", nullable = false)
    private Pelicula pelicula;

    @ManyToOne
    @JoinColumn(name = "fk_id_usuario", nullable = false)
    private Usuario usuario;

    @ManyToOne
    @JoinColumn(name = "fk_id_multiplex", nullable = false)
    private Multiplex multiplex;

    @Column(name = "puntaje", nullable = false, length = 1)
    private Integer puntaje;

    @JsonIgnore
    @Column(name = "estado", nullable = false, length = 1)
    private String estado = ACTIVO;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        PeliculaPuntaje peliculaPuntaje = (PeliculaPuntaje) o;
        return id != null && Objects.equals(id, peliculaPuntaje.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }

}
