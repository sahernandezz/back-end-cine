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
import java.util.Date;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Table(name = "cliente_puntaje")
@Entity
public class PuntajeUsuario implements Serializable {

    @Serial
    private static final long serialVersionUID = 13425L;

    public static final String ACTIVO = "A";
    public static final String INACTIVO = "I";
    public static final String DESICNADO = "D";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_cliente_puntaje")
    private Integer id;

    @Column(name = "puntaje", nullable = false, length = 5)
    private Integer puntaje;

    @Column(name = "fecha", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date fecha;

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
        PuntajeUsuario puntajeUsuario = (PuntajeUsuario) o;
        return id != null && puntajeUsuario.id != null && id.equals(puntajeUsuario.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
