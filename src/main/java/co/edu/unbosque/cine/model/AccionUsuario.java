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
@Table(name = "accion_usuario")
@Entity
public class AccionUsuario implements Serializable {

    @Serial
    private static final long serialVersionUID = 2221L;

    public static final String ACTIVO = "A";
    public static final String INACTIVO = "I";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_accion_usuario")
    private Integer id;

    @Column(name = "nombre", nullable = false, length = 50)
    private String nombre;

    @JsonIgnore
    @Column(name = "estado", nullable = false, length = 1)
    private String estado = ACTIVO;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        AccionUsuario accionUsuario = (AccionUsuario) o;
        return id != null && Objects.equals(id, accionUsuario.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
