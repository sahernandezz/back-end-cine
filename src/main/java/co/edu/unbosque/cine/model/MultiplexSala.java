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
@Table(name = "multiplex_sala")
@Entity
public class MultiplexSala implements Serializable {

    @Serial
    private static final long serialVersionUID = 74211L;

    public static final String ACTIVO = "A";
    public static final String INACTIVO = "I";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_multiplex_sala")
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "fk_id_multiplex", nullable = false)
    private Multiplex multiplex;

    @Column(name = "numero_sala", nullable = false, length = 2)
    private String numeroSala;

    @Column(name = "numero_sillas_localidad_general", nullable = false, length = 2)
    private Integer sillasGeneral;

    @Column(name = "numero_sillas_localidad_preferencial", nullable = false, length = 2)
    private Integer sillasPreferencial;

    @Column(name = "numero_filas", nullable = false, length = 2)
    private Integer numeroFilas;

    @Column(name = "numero_columnas", nullable = false, length = 2)
    private Integer numeroColumnas;

    @ManyToOne
    @JoinColumn(name = "fk_multiplex_tipo_sala", nullable = false)
    private TipoSala tipoSala;

    @JsonIgnore
    @Column(name = "estado", nullable = false, length = 1)
    private String estado = ACTIVO;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        MultiplexSala multiplexSala = (MultiplexSala) o;
        return id != null && Objects.equals(id, multiplexSala.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
