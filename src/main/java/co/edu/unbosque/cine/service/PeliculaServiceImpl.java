package co.edu.unbosque.cine.service;

import co.edu.unbosque.cine.model.*;
import co.edu.unbosque.cine.repository.PeliculaPuntajeRepositoryImpl;
import co.edu.unbosque.cine.repository.PeliculaRepositoryImpl;
import co.edu.unbosque.cine.request.PuntajePeliculaRequest;
import co.edu.unbosque.cine.response.PeliculaPuntajeResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PeliculaServiceImpl {

    /**
     * Inyección de dependencias
     */
    private PeliculaRepositoryImpl peliculaRepository;

    /**
     * Inyección de dependencias
     */
    private PeliculaPuntajeRepositoryImpl peliculaPuntajeRepository;

    /**
     * Constructor
     *
     * @param peliculaRepository        PeliculaRepositoryImpl
     * @param peliculaPuntajeRepository PeliculaPuntajeRepositoryImpl
     */
    @Autowired
    public PeliculaServiceImpl(PeliculaRepositoryImpl peliculaRepository, PeliculaPuntajeRepositoryImpl peliculaPuntajeRepository) {
        this.peliculaRepository = peliculaRepository;
        this.peliculaPuntajeRepository = peliculaPuntajeRepository;
    }

    /**
     * Método que permite listar las películas por ciudad en cartelera
     *
     * @param idCiudad ciudad
     * @return Listado de películas
     */
    public List<Pelicula> listaPeliculasPorCiudadEnCartelera(final Integer idCiudad) {
        return peliculaRepository.listaPorCiudadEstadoPeliculaMultiplex(idCiudad, Ciudad.ACTIVO, Pelicula.ACTIVO, MultiplexPelicula.EN_CARTELERA, Multiplex.ACTIVO);
    }

    /**
     * Método que permite listar las películas por ciudad próximas a estrenar
     *
     * @param idCiudad ciudad
     * @return Listado de películas
     */
    public List<Pelicula> listaPeliculasPorCiudadProximamente(final Integer idCiudad) {
        return peliculaRepository.listaPorCiudadEstadoPeliculaMultiplex(idCiudad, Ciudad.ACTIVO, Pelicula.ACTIVO, MultiplexPelicula.PROXIMAMENTE, Multiplex.ACTIVO);
    }

    /**
     * Método que permite listar las películas por ciudad y titulo
     *
     * @param idCiudad ciudad
     * @param titulo   titulo
     * @return Listado de películas por ciudad y titulo
     */
    public List<Pelicula> listaPeliculasPorCiudadTitulo(final Integer idCiudad, final String titulo) {
        return peliculaRepository.listaPorCiudadEstadoPorTituloMultiplex(idCiudad, Ciudad.ACTIVO, titulo, Pelicula.ACTIVO, MultiplexPelicula.PROXIMAMENTE, MultiplexPelicula.EN_CARTELERA, Multiplex.ACTIVO);
    }

    /**
     * Método que permite obtener el promedio de calificación de una película
     *
     * @param idPelicula id de la pelicula
     * @return promedio de calificación
     */
    public Integer puntajePelicula(final Integer idPelicula) {
        return peliculaRepository.puntajePorPelicula(idPelicula);
    }

    /**
     * Método que permite obtener el puntaje de una pelicula por usuario
     *
     * @param idPelicula identificador de la pelicula
     * @param idUsuario  identificador del usuario
     * @return puntaje de la pelicula
     */
    public Optional<PeliculaPuntajeResponse> valoracionPeliculaCliente(final Integer idPelicula, final Integer idUsuario) {
        Optional<PeliculaPuntaje> p = this.peliculaPuntajeRepository.puntajePorPeliculaPorUsuario(idPelicula, idUsuario);
        Optional<PeliculaPuntajeResponse> peliculaPuntajeResponse = Optional.empty();
        if (p.isPresent()) {
            peliculaPuntajeResponse = Optional.of(this.createPeliculaPuntajeResponse(p.get()));
        }
        return peliculaPuntajeResponse;
    }

    /**
     * Método que permite guardar el puntaje de una pelicula por usuario
     *
     * @param peliculaPuntaje peliculaPuntaje
     * @return PeliculaPuntaje
     */
    public Optional<PeliculaPuntajeResponse> guardarPuntajePelicula(final PuntajePeliculaRequest peliculaPuntaje) {
        Optional<PeliculaPuntaje> p = this.peliculaPuntajeRepository.guardarPuntaje(this.createPeliculaPuntaje(peliculaPuntaje));
        Optional<PeliculaPuntajeResponse> peliculaPuntajeResponse = Optional.empty();
        if (p.isPresent()) {
            peliculaPuntajeResponse = Optional.of(this.createPeliculaPuntajeResponse(p.get()));
        }
        return peliculaPuntajeResponse;
    }

    /**
     * Método que permite crear un objeto PeliculaPuntaje
     *
     * @param p PuntajePeliculaRequest
     * @return PeliculaPuntaje
     */
    private PeliculaPuntaje createPeliculaPuntaje(final PuntajePeliculaRequest p) {
        PeliculaPuntaje peliculaPuntaje = new PeliculaPuntaje();
        peliculaPuntaje.setId(p.getId());
        peliculaPuntaje.setPelicula(p.getPelicula());
        peliculaPuntaje.setUsuario(p.getUsuario());
        peliculaPuntaje.setMultiplex(p.getMultiplex());
        peliculaPuntaje.setPuntaje(p.getPuntaje());
        return peliculaPuntaje;
    }

    private PeliculaPuntajeResponse createPeliculaPuntajeResponse(final PeliculaPuntaje peliculaPuntaje) {
        PeliculaPuntajeResponse peliculaPuntajeResponse = new PeliculaPuntajeResponse();
        peliculaPuntajeResponse.setId(peliculaPuntaje.getId());
        peliculaPuntajeResponse.setPuntaje(peliculaPuntaje.getPuntaje());
        return peliculaPuntajeResponse;
    }
}