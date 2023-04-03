package co.edu.unbosque.cine.repository.jpa;

import co.edu.unbosque.cine.model.PuntajeUsuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface PuntajeUsuarioJpaRepositoryImpl extends JpaRepository<PuntajeUsuario, Integer> {

    @Query("SELECT SUM(p.puntaje) FROM PuntajeUsuario p WHERE p.usuario.id = :id_usuario")
    Integer puntosUsuario(final @Param("id_usuario") Integer idUsuario);

}
