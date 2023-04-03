package co.edu.unbosque.cine.repository;

import co.edu.unbosque.cine.model.PuntajeUsuario;
import co.edu.unbosque.cine.repository.jpa.PuntajeUsuarioJpaRepositoryImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.Optional;

@Repository
public class PuntajeUsuarioRepositoryImpl {

    /**
     * Inyección de dependencias
     */
    private PuntajeUsuarioJpaRepositoryImpl puntajeJpaRepository;

    /**
     * Constructor
     *
     * @param puntajeJpaRepository Inyección de dependencias
     */
    @Autowired
    public PuntajeUsuarioRepositoryImpl(PuntajeUsuarioJpaRepositoryImpl puntajeJpaRepository) {
        this.puntajeJpaRepository = puntajeJpaRepository;
    }

    /**
     * Obtener los puntos de un usuario
     *
     * @param idUsuario id del usuario
     * @return puntos del usuario
     */
    public Integer puntosUsuario(final Integer idUsuario) {
        return this.puntajeJpaRepository.puntosUsuario(idUsuario);
    }

    /**
     * Guardar un puntaje
     *
     * @param puntajeUsuario puntaje a guardar
     * @return puntaje guardado
     */
    @Transactional
    public Optional<PuntajeUsuario> guardarPuntaje(final PuntajeUsuario puntajeUsuario) {
        return Optional.of(this.puntajeJpaRepository.save(puntajeUsuario));
    }
}
