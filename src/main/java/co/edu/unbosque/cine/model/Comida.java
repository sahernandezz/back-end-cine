package co.edu.unbosque.cine.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
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
@Table(name = "comida")
@Entity
public class Comida implements Serializable {

    @Serial
    private static final long serialVersionUID = 32023L;

    public static final String ACTIVO = "A";
    public static final String INACTIVO = "I";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_comida")
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "fk_id_pais", nullable = false)
    private Pais pais;

    @ManyToOne
    @JsonIgnoreProperties({"comidas"})
    @JoinColumn(name = "fk_id_categoria_comida", nullable = false)
    private CategoriaComida categoria;

    @Column(name = "nombre", nullable = false, length = 150)
    private String nombre;

    @Column(name = "imagen", nullable = false, length = 250)
    private String imagen;

    @Column(name = "descripcion", length = 250)
    private String descripcion;

    @JsonIgnore
    @Column(name = "estado", nullable = false, length = 1)
    private String estado = ACTIVO;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Comida comida = (Comida) o;
        return id != null && Objects.equals(id, comida.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
