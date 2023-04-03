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
@Table(name = "multiplex_comida_factura")
@Entity
public class FacturaComida implements Serializable {

    @Serial
    private static final long serialVersionUID = 36423L;

    public static final String ACTIVO = "A";
    public static final String INACTIVO = "I";
    public static final String PAGADO = "P";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_multiplex_comida_factura")
    private Integer id;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "fk_id_usuario", nullable = false)
    private Usuario usuario;

    @ManyToOne
    @JoinColumn(name = "fk_id_multiplex", nullable = false)
    private Multiplex multiplex;

    @ManyToOne
    @JsonIgnoreProperties("usuario")
    @JoinColumn(name = "fk_id_cliente_puntaje", nullable = false)
    private PuntajeUsuario puntaje;

    @ManyToOne
    @JoinColumn(name = "fk_id_articulo", nullable = false)
    private Articulo articulo;

    @Column(name = "total_valor", nullable = false)
    private Integer valor;

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
        FacturaComida facturaComida = (FacturaComida) o;
        return id != null && Objects.equals(id, facturaComida.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
