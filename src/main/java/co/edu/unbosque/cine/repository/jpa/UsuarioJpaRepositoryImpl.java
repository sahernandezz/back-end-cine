package co.edu.unbosque.cine.repository.jpa;

import co.edu.unbosque.cine.model.TipoDocumento;
import co.edu.unbosque.cine.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface UsuarioJpaRepositoryImpl extends JpaRepository<Usuario, Integer> {

    /**
     * Busca un usuario por correo
     *
     * @param correo correo del usuario
     * @return usuario
     */
    Usuario findByCorreo(final String correo);

    /**
     * Busca un usuario por telefono
     *
     * @param telefono telefono del usuario
     * @return usuario
     */
    Usuario findByTelefono(final String telefono);

    /**
     * Busca un usuario por codigo de empleado
     *
     * @param codigoEmpleado codigo de empleado del usuario
     * @return usuario
     */
    Usuario findByCodigoEmpleado(final String codigoEmpleado);

    /**
     * Busca un usuario por numero de documento
     *
     * @param numeroDocumento numero de documento del usuario
     * @param tipoDocumento   tipo de documento del usuario
     * @return usuario
     */
    Usuario findByNumeroDocumentoAndTipoDocumento(final String numeroDocumento, final TipoDocumento tipoDocumento);

    @Query("SELECT usuario_rol.usuario FROM  UsuarioRol usuario_rol " +
            "WHERE usuario_rol.usuario.multiplex.id = :id_multiplex " +
            "AND usuario_rol.rol.id = :rol_a " +
            "OR usuario_rol.usuario.multiplex.id = :id_multiplex " +
            "AND usuario_rol.rol.id = :rol_b "
    )
    List<Usuario> listaUsuariosDirector(final @Param("id_multiplex") Integer idMultiplex, final @Param("rol_a") Integer rolA, final @Param("rol_b") Integer rolB);
}
