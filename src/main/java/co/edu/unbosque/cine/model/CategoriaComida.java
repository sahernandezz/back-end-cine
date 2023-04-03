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
import java.util.List;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Table(name = "categoria_comida")
@Entity
public class CategoriaComida implements Serializable {

    @Serial
    private static final long serialVersionUID = 18240L;

    public static final String ACTIVO = "A";
    public static final String INACTIVO = "I";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_categoria_comida")
    private Integer id;

    @Column(name = "imagen", length = 250, nullable = false)
    private String imagen;

    @Column(name = "nombre", length = 50, nullable = false, unique = true)
    private String nombre;

    @OneToMany(mappedBy = "categoria", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @ToString.Exclude
    @JsonIgnoreProperties({"categoria"})
    private List<Comida> comidas;

    @Column(name = "estado", nullable = false, length = 1)
    private String estado = ACTIVO;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        CategoriaComida categoriaComida = (CategoriaComida) o;
        return id != null && categoriaComida.id != null && id.equals(categoriaComida.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
