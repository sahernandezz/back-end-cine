package co.edu.unbosque.cine.repository;

import co.edu.unbosque.cine.model.UsuarioRol;
import co.edu.unbosque.cine.repository.jpa.UsuarioRolJpaRepositoryImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Repository
public class UsuarioRolRepositoryImpl {

    /**
     * Inyección de dependencias
     */
    private UsuarioRolJpaRepositoryImpl usuarioJpa;

    /**
     * Constructor
     *
     * @param usuarioJpa usuarioJpa
     */
    @Autowired
    public UsuarioRolRepositoryImpl(UsuarioRolJpaRepositoryImpl usuarioJpa) {
        this.usuarioJpa = usuarioJpa;
    }

    /**
     * Método que permite guardar el usuario rol
     *
     * @param usuarioRol usuarioRol
     * @return UsuarioRol
     */
    public Optional<UsuarioRol> guardarUsuarioRol(final UsuarioRol usuarioRol) {
        return Optional.of(usuarioJpa.save(usuarioRol));
    }

    public Optional<List<UsuarioRol>> guardarUsuarioRol(final List<UsuarioRol> usuarioRol) {
        return Optional.of(usuarioJpa.saveAll(usuarioRol));
    }

    /**
     * Método que permite eliminar todos los roles de un usuario
     *
     * @param idUsuario idUsuario
     */
    @Transactional
    public void eliminarRoles(final Integer idUsuario) {
        usuarioJpa.eliminarRoles(idUsuario);
    }
}
