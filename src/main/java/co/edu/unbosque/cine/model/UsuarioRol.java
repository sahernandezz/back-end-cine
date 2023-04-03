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

@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Table(name = "usuario_rol")
@Entity
public class UsuarioRol implements Serializable {

    @Serial
    private static final long serialVersionUID = 124321L;

    public static final String ACTIVO = "A";
    public static final String INACTIVO = "I";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_usuario_rol")
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "fk_id_rol", nullable = false)
    private Rol rol;

    @ManyToOne
    @JoinColumn(name = "fk_id_usuario", nullable = false)
    private Usuario usuario;

    @JsonIgnore
    @Column(name = "estado", nullable = false, length = 1)
    private String estado = ACTIVO;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        UsuarioRol usuarioRol = (UsuarioRol) o;
        return id != null && usuarioRol.id != null && id.equals(usuarioRol.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
