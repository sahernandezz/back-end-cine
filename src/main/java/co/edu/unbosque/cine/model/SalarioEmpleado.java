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
@Table(name = "empleado_salario")
@Entity
public class SalarioEmpleado implements Serializable {

    @Serial
    private static final long serialVersionUID = 63221L;

    public static final String ACTIVO = "A";
    public static final String INACTIVO = "I";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_empleado_salario")
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "fk_id_usuario", nullable = false, unique = true)
    private Usuario usuario;

    @Temporal(TemporalType.DATE)
    @Column(name = "fecha_inicio", nullable = false)
    private Date fechaInicio;

    @Column(name = "fecha_fin")
    private Date fechaFin;

    @Column(name = "salario", nullable = false)
    private Float salario;

    @JsonIgnore
    @Column(name = "estado", nullable = false, length = 1)
    private String estado = ACTIVO;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        SalarioEmpleado salarioEmpleado = (SalarioEmpleado) o;
        return id != null && Objects.equals(id, salarioEmpleado.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
