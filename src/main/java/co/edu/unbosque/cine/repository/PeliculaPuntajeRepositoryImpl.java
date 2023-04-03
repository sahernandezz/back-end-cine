package co.edu.unbosque.cine.repository;

import co.edu.unbosque.cine.model.PeliculaPuntaje;
import co.edu.unbosque.cine.repository.jpa.PeliculaPuntajeJpaRepositoryImpl;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class PeliculaPuntajeRepositoryImpl {

    /**
     * jpa repository pelicula puntaje
     */
    private PeliculaPuntajeJpaRepositoryImpl peliculaPuntajeJpa;

    /**
     * Constructor
     *
     * @param p Pelicula puntaje jpa
     */
    public PeliculaPuntajeRepositoryImpl(PeliculaPuntajeJpaRepositoryImpl p) {
        this.peliculaPuntajeJpa = p;
    }

    /**
     * Método que permite obtener el puntaje de una pelicula por usuario
     *
     * @param idPelicula identificador de la pelicula
     * @param idUsuario  identificador del usuario
     * @return puntaje de la pelicula
     */
    public Optional<PeliculaPuntaje> puntajePorPeliculaPorUsuario(final Integer idPelicula, final Integer idUsuario) {
        return Optional.of(this.peliculaPuntajeJpa.puntajePorPeliculaPorUsuario(idPelicula, idUsuario));
    }

    /**
     * Método que permite guardar el puntaje de una pelicula por usuario
     *
     * @param peliculaPuntaje pelicula puntaje
     * @return pelicula puntaje
     */
    public Optional<PeliculaPuntaje> guardarPuntaje(final PeliculaPuntaje peliculaPuntaje) {
        return Optional.of(this.peliculaPuntajeJpa.save(peliculaPuntaje));
    }
}
