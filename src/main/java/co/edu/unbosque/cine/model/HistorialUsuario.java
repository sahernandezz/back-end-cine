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
@Table(name = "historial_usuario")
@Entity
public class HistorialUsuario implements Serializable {

    @Serial
    private static final long serialVersionUID = 32323L;

    public static final String ACTIVO = "A";
    public static final String INACTIVO = "I";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_historial_empleado")
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "fk_id_usuario", nullable = false)
    private Usuario usuario;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "fecha", nullable = false)
    private Date fecha;

    @Column(name = "descripcion", nullable = false, length = 50)
    private String descripcion;

    @ManyToOne
    @JoinColumn(name = "fk_id_accion", nullable = false)
    private AccionUsuario accion;

    @JsonIgnore
    @Column(name = "estado", nullable = false, length = 1)
    private String estado = ACTIVO;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        HistorialUsuario historialUsuario = (HistorialUsuario) o;
        return id != null && Objects.equals(id, historialUsuario.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
