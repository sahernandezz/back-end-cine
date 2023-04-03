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
@Table(name = "ciudad")
@Entity
public class Ciudad implements Serializable {

    @Serial
    private static final long serialVersionUID = 42125L;

    public static final String ACTIVO = "A";
    public static final String INACTIVO = "I";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_ciudad")
    private Integer id;

    @Column(name = "codigo", nullable = false, length = 10, unique = true)
    private String codigo;

    @Column(name = "nombre", nullable = false, length = 50, unique = true)
    private String nombre;

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
        Ciudad ciudad = (Ciudad) o;
        return id != null && ciudad.id != null && id.equals(ciudad.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
