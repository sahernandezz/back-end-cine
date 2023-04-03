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
@Table(name = "multiplex_funcion_sala_silla")
@Entity
public class FuncionSalaSilla implements Serializable {

    @Serial
    private static final long serialVersionUID = 3223L;

    public static final String ACTIVO = "A";
    public static final String INACTIVO = "I";
    public static final String PAGO = "P";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_multiplex_funcion_sala_silla")
    private Integer id;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "fk_id_multiplex_funcion_factura", nullable = false)
    private FacturaFuncion facturaFuncion;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "fk_id_multiplex_funcion", nullable = false)
    private Funcion funcion;

    @Column(name = "fila", nullable = false, length = 1)
    private String fila;

    @Column(name = "columna", nullable = false, length = 1)
    private String columna;

    @Column(name = "tipo_silla", nullable = false, length = 1)
    private String tipoSilla;

    @JsonIgnore
    @Column(name = "estado", nullable = false, length = 1)
    private String estado = ACTIVO;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        FuncionSalaSilla funcionSalaSilla = (FuncionSalaSilla) o;
        return id != null && Objects.equals(id, funcionSalaSilla.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
