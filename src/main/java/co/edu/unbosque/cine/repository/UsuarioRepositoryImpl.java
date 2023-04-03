package co.edu.unbosque.cine.repository;

import co.edu.unbosque.cine.model.TipoDocumento;
import co.edu.unbosque.cine.model.Usuario;
import co.edu.unbosque.cine.repository.jpa.UsuarioJpaRepositoryImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class UsuarioRepositoryImpl {

    /**
     * jpa repository usuario
     */
    private UsuarioJpaRepositoryImpl usuarioJpa;

    /**
     * Constructor
     *
     * @param u usuario jpa
     */
    @Autowired
    public UsuarioRepositoryImpl(UsuarioJpaRepositoryImpl u) {
        this.usuarioJpa = u;
    }

    /**
     * lista de usuarios
     *
     * @return lista de usuarios
     */
    public List<Usuario> listarUsuarios() {
        return this.usuarioJpa.findAll();
    }

    /**
     * Metodo que permite guardar un usuario
     *
     * @param usuario usuario
     * @return optional usuario
     */
    public Optional<Usuario> guardarUsuario(final Usuario usuario) {
        return Optional.of(this.usuarioJpa.save(usuario));
    }

    /**
     * Metodo que permite buscar un usuario por correo
     *
     * @param correo correo
     * @return usuario
     */
    public Optional<Usuario> buscarPorCorreo(final String correo) {
        return Optional.of(this.usuarioJpa.findByCorreo(correo));
    }

    /**
     * Metodo que permite buscar un usuario por codigo de empleado
     *
     * @param codigoEmpleado codigo de empleado
     * @return usuario
     */
    public Optional<Usuario> buscarPorCodigoEmpleado(final String codigoEmpleado) {
        return Optional.of(this.usuarioJpa.findByCodigoEmpleado(codigoEmpleado));
    }

    /**
     * Metodo que permite buscar un usuario por id
     *
     * @param id id
     * @return usuario
     */
    public Optional<Usuario> buscarPorId(final Integer id) {
        return this.usuarioJpa.findById(id);
    }

    /**
     * Metodo que permite buscar un usuario por numero de documento y tipo de documento
     *
     * @param numeroDocumento numero de documento
     * @param tipoDocumento   tipo de documento
     * @return usuario
     */
    public Optional<Usuario> buscarPorNumeroDocumentoAndTipoDocumento(final String numeroDocumento, final TipoDocumento tipoDocumento) {
        return Optional.ofNullable(this.usuarioJpa.findByNumeroDocumentoAndTipoDocumento(numeroDocumento, tipoDocumento));
    }

    /**
     * Metodo que permite obtener la lista de usuarios del director
     *
     * @param idMultiplex id del multiplex del director
     * @param rolA        rol a
     * @param rolB        rol b
     * @return lista de usuarios
     */
    public List<Usuario> listarUsuariosDirector(final Integer idMultiplex, final Integer rolA, final Integer rolB) {
        return this.usuarioJpa.listaUsuariosDirector(idMultiplex, rolA, rolB);
    }
}
