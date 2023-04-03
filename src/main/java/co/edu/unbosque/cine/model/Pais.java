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
@Table(name = "pais")
@Entity
public class Pais implements Serializable {

    @Serial
    private static final long serialVersionUID = 33525L;

    public static final String ACTIVO = "A";
    public static final String INACTIVO = "I";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_pais")
    private Integer id;

    @Column(name = "iso2", nullable = false, length = 2, unique = true)
    private String iso2;

    @Column(name = "iso3", nullable = false, length = 3, unique = true)
    private String iso3;

    @Column(name = "nombre", nullable = false, length = 50, unique = true)
    private String nombre;

    @JsonIgnore
    @Column(name = "estado", nullable = false, length = 1)
    private String estado = ACTIVO;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Pais pais = (Pais) o;
        return id != null && pais.id != null && id.equals(pais.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
