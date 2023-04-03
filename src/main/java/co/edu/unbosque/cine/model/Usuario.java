package co.edu.unbosque.cine.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.Hibernate;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.io.Serial;
import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Table(name = "usuario")
@Entity
public class Usuario implements Serializable, UserDetails {

    @Serial
    private static final long serialVersionUID = 132425L;

    public static final String ACTIVO = "A";
    public static final String INACTIVO = "I";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_usuario")
    private Integer id;

    @Column(name = "nombre", nullable = false, length = 50)
    private String nombre;

    @Column(name = "apellido", nullable = false, length = 50)
    private String apellido;

    @Column(name = "correo", nullable = false, length = 50, unique = true)
    private String correo;

    @Column(name = "clave", nullable = false, length = 250)
    private String clave;

    @Column(name = "telefono", nullable = false, length = 25, unique = true)
    private String telefono;

    @Column(name = "direccion", length = 50)
    private String direccion;

    @Column(name = "codigo_empleado", length = 15, unique = true)
    private String codigoEmpleado;

    @Column(name = "codigo_recuperacion", length = 25)
    private String codigoRecuperacion;

    @Column(name = "fecha_recuperacion")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaRecuperacion;

    @ManyToOne
    @JoinColumn(name = "fk_id_tipo_documento", nullable = false)
    private TipoDocumento tipoDocumento;

    @Column(name = "numero_documento", nullable = false, length = 25)
    private String numeroDocumento;

    @ManyToOne
    @JoinColumn(name = "fk_id_multiplex")
    private Multiplex multiplex;

    @ManyToMany(cascade = {CascadeType.ALL}, fetch = FetchType.EAGER)
    @JoinTable(name = "usuario_rol",
            joinColumns = {@JoinColumn(name = "fk_id_usuario")},
            inverseJoinColumns = {@JoinColumn(name = "fk_id_rol")})
    private List<Rol> roles;

    @Column(name = "estado", nullable = false, length = 1)
    private String estado = ACTIVO;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.roles;
    }

    @Override
    public String getPassword() {
        return this.clave;
    }

    @Override
    public String getUsername() {
        return this.correo;
    }

    @JsonIgnore
    @Override
    public boolean isAccountNonExpired() {
        return this.isEnabled();
    }

    @JsonIgnore
    @Override
    public boolean isAccountNonLocked() {
        return this.isEnabled();
    }

    @JsonIgnore
    @Override
    public boolean isCredentialsNonExpired() {
        return this.isEnabled();
    }

    @Override
    public boolean isEnabled() {
        return this.estado.equals(ACTIVO);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Usuario usuario = (Usuario) o;
        return id != null && usuario.id != null && id.equals(usuario.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
