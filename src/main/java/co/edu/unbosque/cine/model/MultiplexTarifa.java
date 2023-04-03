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
@Table(name = "multiplex_tarifa_funcion")
@Entity
public class MultiplexTarifa implements Serializable {

    @Serial
    private static final long serialVersionUID = 19311L;

    public static final String ACTIVO = "A";
    public static final String INACTIVO = "I";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_multiplex_tarifa_funcion")
    private Integer id;

    @Column(name = "valor_localidad_general", nullable = false)
    private Float valorGeneral;

    @Column(name = "valor_localidad_preferencial", nullable = false)
    private Float valorPreferencial;

    @ManyToOne
    @JoinColumn(name = "fk_id_multiplex_tipo_sala", nullable = false)
    private TipoSala tipoSala;

    @ManyToOne
    @JoinColumn(name = "fk_id_multiplex_sala_tipo_idioma", nullable = false)
    private SalaTipoIdioma salaIdioma;

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
        MultiplexTarifa multiplexTarifa = (MultiplexTarifa) o;
        return id != null && Objects.equals(id, multiplexTarifa.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
