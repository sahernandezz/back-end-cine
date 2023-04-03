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
@Table(name = "multiplex_tarifa_comida")
@Entity
public class TarifaComida implements Serializable {

    @Serial
    private static final long serialVersionUID = 43311L;

    public static final String ACTIVO = "A";
    public static final String INACTIVO = "I";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_multiplex_tarifa_comida")
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "fk_id_multiplex", nullable = false)
    private Multiplex multiplex;

    @ManyToOne
    @JoinColumn(name = "fk_id_comida", nullable = false)
    private Comida comida;

    @Column(name = "valor", nullable = false)
    private Float valor;

    @JsonIgnore
    @Column(name = "estado", nullable = false, length = 1)
    private String estado = ACTIVO;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        TarifaComida tarifaComida = (TarifaComida) o;
        return id != null && Objects.equals(id, tarifaComida.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }


}
