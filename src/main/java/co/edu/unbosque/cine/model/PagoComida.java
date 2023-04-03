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
@Table(name = "multiplex_pago_comida")
@Entity
public class PagoComida implements Serializable {

    @Serial
    private static final long serialVersionUID = 320921L;

    public static final String ACTIVO = "A";
    public static final String INACTIVO = "I";
    public static final String PAGO = "P";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_multiplex_pago_comida")
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "fk_id_multiplex_comida_factura", nullable = false)
    private FacturaComida facturaComida;

    @ManyToOne
    @JoinColumn(name = "fk_id_medio_pago", nullable = false)
    private MedioPago medioPago;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "fecha", nullable = false)
    private Date fecha;

    @JoinColumn(name = "pago", nullable = false)
    private Float pago;

    @JsonIgnore
    @Column(name = "estado", nullable = false, length = 1)
    private String estado = ACTIVO;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        PagoComida pagoComida = (PagoComida) o;
        return id != null && Objects.equals(id, pagoComida.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
