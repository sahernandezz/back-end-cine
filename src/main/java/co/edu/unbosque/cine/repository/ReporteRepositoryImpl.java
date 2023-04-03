package co.edu.unbosque.cine.repository;

import co.edu.unbosque.cine.model.Pelicula;
import co.edu.unbosque.cine.repository.jpa.FacturaFuncionJpaRepositoryImpl;
import co.edu.unbosque.cine.repository.jpa.PeliculaJpaRepositoryImpl;
import co.edu.unbosque.cine.repository.jpa.PeliculaPuntajeJpaRepositoryImpl;
import co.edu.unbosque.cine.response.PeliculaReporteResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class ReporteRepositoryImpl {
    /**
     * Inyección de dependencias
     */
    private PeliculaPuntajeJpaRepositoryImpl peliculaPuntajeJpaRepository;

    /**
     * Inyección de dependencias
     */
    private PeliculaJpaRepositoryImpl peliculaJpaRepository;


    /**
     * Constructor
     *
     * @param peliculaPuntajeJpaRepository
     * @param peliculaJpaRepository
     */
    @Autowired
    public ReporteRepositoryImpl(PeliculaPuntajeJpaRepositoryImpl peliculaPuntajeJpaRepository, PeliculaJpaRepositoryImpl peliculaJpaRepository) {
        this.peliculaPuntajeJpaRepository = peliculaPuntajeJpaRepository;
        this.peliculaJpaRepository = peliculaJpaRepository;
    }

    /**
     * Películas con puntaje mayor
     *
     * @param limite cantidad de películas a mostrar
     * @return lista de películas
     */
    public List<PeliculaReporteResponse> peliculasConPuntajeMayor(final Integer limite) {
        List<Pelicula> peliculas = peliculaPuntajeJpaRepository.peliculasConPuntajeMayor();
        List<PeliculaReporteResponse> peliculasReporte = new ArrayList<>();
        int contador = peliculas.size() >= limite ? limite : peliculas.size();
        for (int i = 0; i < contador; i++) {
            PeliculaReporteResponse peliculaReporte = new PeliculaReporteResponse();
            peliculaReporte.setPuntaje(peliculaJpaRepository.puntajePorPelicula(peliculas.get(i).getId()));
            peliculaReporte.setPelicula(peliculas.get(i));
            peliculasReporte.add(peliculaReporte);
        }
        return peliculasReporte;
    }

    /**
     * numero de peliculas por estado
     *
     * @param estado estado de la pelicula
     * @return numero de peliculas
     */
    public Integer numeroPeliculasPorEstado(final String estado) {
        return peliculaJpaRepository.cantidadPeliculas(estado);
    }
}
