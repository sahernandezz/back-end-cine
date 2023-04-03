package co.edu.unbosque.cine.model;

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
@Table(name = "articulo")
@Entity
public class Articulo implements Serializable {

    @Serial
    private static final long serialVersionUID = 4643L;

    public static final String ACTIVO = "A";
    public static final String INACTIVO = "I";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_articulo")
    private Integer id;

    @Column(name = "nombre", length = 50, nullable = false)
    private String nombre;

    @Column(name = "codigo", length = 5, nullable = false)
    private Integer codigo;

    @Column(name = "porcentaje_impuesto", length = 50, nullable = false)
    private float porcentajeImpuesto;

    @ManyToOne
    @JoinColumn(name = "fk_id_pais", nullable = false)
    private Pais pais;

    @Column(name = "estado", nullable = false, length = 1)
    private String estado = ACTIVO;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Articulo articulo = (Articulo) o;
        return id != null && Objects.equals(id, articulo.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }

}
