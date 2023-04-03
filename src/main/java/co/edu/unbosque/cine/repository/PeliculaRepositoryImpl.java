package co.edu.unbosque.cine.repository;

import co.edu.unbosque.cine.model.Pelicula;
import co.edu.unbosque.cine.model.PuntajeUsuario;
import co.edu.unbosque.cine.repository.jpa.PeliculaJpaRepositoryImpl;
import co.edu.unbosque.cine.repository.jpa.PeliculaPuntajeJpaRepositoryImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class PeliculaRepositoryImpl {

    /**
     * jpa repository pelicula
     */
    private PeliculaJpaRepositoryImpl peliculaJpa;


    /**
     * Constructor
     *
     * @param p película jpa
     */
    @Autowired
    public PeliculaRepositoryImpl(PeliculaJpaRepositoryImpl p) {
        this.peliculaJpa = p;
    }


    /**
     * Metodo que permite obtener todas las películas por ciudad y estado del multiplex
     *
     * @param idCiudad                id de la ciudad
     * @param estadoCiudad            estado de la ciudad
     * @param estadoPeliculaMultiplex estado película multiplex
     * @param estadoMultiplex         estado del multiplex
     * @return lista de películas
     */
    public List<Pelicula> listaPorCiudadEstadoPeliculaMultiplex(final Integer idCiudad, final String estadoCiudad, final String estadoPelicula, final String estadoPeliculaMultiplex, final String estadoMultiplex) {
        return peliculaJpa.listaPorCiudadEstadoMultiplex(idCiudad, estadoCiudad, estadoPelicula, estadoPeliculaMultiplex, estadoMultiplex);
    }

    /**
     * Metodo que permite obtener todas las películas por ciudad, estado del multiplex y titulo pelicula
     *
     * @param idCiudad                  id de la ciudad
     * @param estadoPelicula            estado de la película
     * @param estadoMultiplex           estado del multiplex
     * @param tituloPelicula            titulo de la película
     * @param estadoPeliculaMultiplex_1 estado del multiplex v1
     * @param estadoPeliculaMultiplex_2 estado del multiplex v2
     * @return lista de películas por titulo
     */
    public List<Pelicula> listaPorCiudadEstadoPorTituloMultiplex(final Integer idCiudad,
                                                                 final String estadoCiudad,
                                                                 final String tituloPelicula,
                                                                 final String estadoPelicula,
                                                                 final String estadoPeliculaMultiplex_1,
                                                                 final String estadoPeliculaMultiplex_2,
                                                                 final String estadoMultiplex) {
        return peliculaJpa.listaPorCiudadEstadoPorTituloMultiplex(idCiudad, estadoCiudad, tituloPelicula, estadoPelicula, estadoPeliculaMultiplex_1, estadoPeliculaMultiplex_2, estadoMultiplex);
    }

    /**
     * Método que permite obtener el promedio del puntaje de una pelicula
     *
     * @param idPelicula identificador de la pelicula
     * @return promedio pelicula
     */
    public Integer puntajePorPelicula(final Integer idPelicula) {
        return this.peliculaJpa.puntajePorPelicula(idPelicula);
    }
}
