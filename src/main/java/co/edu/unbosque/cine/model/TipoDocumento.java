package co.edu.unbosque.cine.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.io.Serializable;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Table(name = "tipo_documento")
@Entity
public class TipoDocumento implements Serializable {

    public static final String ACTIVO = "A";
    public static final String INACTIVO = "I";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_tipo_documento")
    private Integer id;

    @Column(name = "nombre", nullable = false, length = 2)
    private String nombre;

    @Column(name = "descripcion", length = 50)
    private String descripcion;

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
        TipoDocumento tipoDocumento = (TipoDocumento) o;
        return id != null && tipoDocumento.id != null && id.equals(tipoDocumento.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
