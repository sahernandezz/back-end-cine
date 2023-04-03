package co.edu.unbosque.cine.repository.jpa;

import co.edu.unbosque.cine.model.Usuario;
import co.edu.unbosque.cine.model.UsuarioRol;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface UsuarioRolJpaRepositoryImpl extends JpaRepository<UsuarioRol, Integer> {

    @Modifying
    @Query("DELETE FROM UsuarioRol u WHERE u.usuario.id = :id_usuario")
    void eliminarRoles(final @Param("id_usuario") Integer idUsuario);
}
