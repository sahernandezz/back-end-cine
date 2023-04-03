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
@Table(name = "medio_pago")
@Entity
public class MedioPago implements Serializable {

    @Serial
    private static final long serialVersionUID = 13231L;

    public static final String ACTIVO = "A";
    public static final String INACTIVO = "I";
    public static final String PAGO_ONLINE = "O";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_medio_pago")
    private Integer id;

    @Column(name = "nombre", nullable = false, length = 50)
    private String nombre;

    @Column(name = "descripcion", length = 50)
    private String descripcion;

    @ManyToOne
    @JoinColumn(name = "fk_id_pais", nullable = false)
    private Pais pais;

    @JsonIgnore
    @Column(name = "estado", nullable = false, length = 1)
    private String estado = ACTIVO;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        MedioPago medioPago = (MedioPago) o;
        return id != null && Objects.equals(id, medioPago.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
