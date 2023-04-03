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
@Table(name = "multiplex_funcion_pago")
@Entity
public class PagoFuncion implements Serializable {
    @Serial
    private static final long serialVersionUID = 212211L;

    public static final String ACTIVO = "A";
    public static final String INACTIVO = "I";
    public static final String PAGO = "P";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_multiplex_funcion_pago")
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "fk_id_multiplex_funcion_factura", nullable = false)
    private FacturaFuncion facturaFuncion;

    @ManyToOne
    @JoinColumn(name = "fk_id_medio_pago", nullable = false)
    private MedioPago medioPago;

    @Column(name = "pago", nullable = false)
    private Float pago;

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
        PagoFuncion pagoFuncion = (PagoFuncion) o;
        return id != null && Objects.equals(id, pagoFuncion.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
