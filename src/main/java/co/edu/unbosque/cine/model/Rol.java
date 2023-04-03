package co.edu.unbosque.cine.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.Hibernate;
import org.springframework.security.core.GrantedAuthority;
//import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;
import java.io.Serial;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Entity
@Table(name = "rol")
public class Rol implements Serializable, GrantedAuthority {

    @Serial
    private static final long serialVersionUID = 74930L;

    public static final String ACTIVO = "A";
    public static final String INACTIVO = "I";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_rol")
    private Integer id;

    @Column(name = "nombre", length = 50, nullable = false, unique = true)
    private String nombre;

    @JsonIgnore
    @Column(name = "estado", nullable = false, length = 1)
    private String estado = ACTIVO;

    @Override
    public String getAuthority() {
        return nombre;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Rol rol = (Rol) o;
        return id != null && Objects.equals(id, rol.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
