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
import java.sql.Time;
import java.time.LocalTime;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.Set;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Entity
@Table(name = "pelicula")
public class Pelicula implements Serializable {

    @Serial
    private static final long serialVersionUID = 12430L;

    public static final String ACTIVO = "A";
    public static final String INACTIVO = "I";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_pelicula")
    private Integer id;

    @Column(name = "titulo", length = 50, nullable = false, unique = true)
    private String titulo;

    @Column(name = "tailer", length = 250, nullable = false)
    private String tailer;

    @Column(name = "imagen", length = 250, nullable = false)
    private String imagen;

    @Column(name = "titulo_original", length = 50, nullable = false, unique = true)
    private String tituloOriginal;

    @Column(name = "estreno", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date estreno;

    @Column(name = "recomendacion", length = 80, nullable = false)
    private String recomendacion;

    @Column(name = "director", length = 50, nullable = false)
    private String director;

    @Column(name = "actores", length = 250, nullable = false)
    private String actores;

    @Column(name = "pais_origen", length = 50, nullable = false)
    private String paisOrigen;

    @Column(name = "lenguaje", length = 25, nullable = false)
    private String lenguaje;

    @Column(name = "duracion", nullable = false)
    private String duracion;

    @ManyToMany(cascade = {CascadeType.ALL}, fetch = FetchType.EAGER)
    @JoinTable(name = "pelicula_genero",
            joinColumns = {@JoinColumn(name = "fk_id_pelicula")},
            inverseJoinColumns = {@JoinColumn(name = "fk_id_genero")})
    private Set<GeneroPelicula> generos;

    @JsonIgnore
    @Column(name = "estado", nullable = false, length = 1)
    private String estado = ACTIVO;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Pelicula pelicula = (Pelicula) o;
        return id != null && Objects.equals(id, pelicula.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
