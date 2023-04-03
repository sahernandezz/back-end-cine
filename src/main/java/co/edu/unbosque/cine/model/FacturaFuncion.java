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
import java.util.Date;
import java.util.Objects;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Table(name = "multiplex_funcion_factura")
@Entity
public class FacturaFuncion implements Serializable {

    @Serial
    private static final long serialVersionUID = 32123L;

    public static final String ACTIVO = "A";
    public static final String INACTIVO = "I";
    public static final String PAGADO = "P";
    public static final String PENDIENTE = "E";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_multiplex_funcion_factura")
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "fk_id_multiplex_funcion", nullable = false)
    private Funcion funcion;

    @ManyToOne
    @JsonIgnoreProperties("usuario")
    @JoinColumn(name = "fk_id_cliente_puntaje", nullable = false)
    private PuntajeUsuario puntaje;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "fk_id_usuario", nullable = false)
    private Usuario usuario;

    @ManyToOne
    @JoinColumn(name = "fk_id_articulo", nullable = false)
    private Articulo articulo;

    @Column(name = "total_valor", nullable = false)
    private Float valor;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "fecha", nullable = false)
    private Date fecha;

    @JsonIgnore
    @Column(name = "estado", nullable = false, length = 1)
    private String estado = ACTIVO;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        FacturaFuncion facturaFuncion = (FacturaFuncion) o;
        return id != null && Objects.equals(id, facturaFuncion.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
