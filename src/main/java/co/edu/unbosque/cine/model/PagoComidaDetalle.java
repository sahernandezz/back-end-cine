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
@Table(name = "multiplex_pago_comida_detalle")
@Entity
public class PagoComidaDetalle implements Serializable {

    @Serial
    private static final long serialVersionUID = 64211L;

    public static final String ACTIVO = "A";
    public static final String INACTIVO = "I";
    public static final String PAGO = "P";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_multiplex_pago_comida_detalle")
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "fk_id_multiplex_comida_factura", nullable = false)
    private FacturaComida facturaComida;

    @ManyToOne
    @JoinColumn(name = "fk_id_multiplex_tarifa_comida", nullable = false)
    private TarifaComida tarifaComida;

    @Column(name = "cantidad", nullable = false, length = 2)
    private Integer cantidad;

    @Column(name = "descripcion", nullable = false, length = 50)
    private Integer descripcion;

    @JsonIgnore
    @Column(name = "estado", nullable = false, length = 1)
    private String estado = ACTIVO;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        PagoComidaDetalle pagoComidaDetalle = (PagoComidaDetalle) o;
        return id != null && Objects.equals(id, pagoComidaDetalle.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
