package co.edu.unbosque.cine.service;

import co.edu.unbosque.cine.model.Pelicula;
import co.edu.unbosque.cine.repository.ReporteRepositoryImpl;
import co.edu.unbosque.cine.response.PeliculaReporteResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReporteServiceImpl {

    /**
     * Inyección de dependencias
     */
    private ReporteRepositoryImpl reporteRepository;


    /**
     * Constructor
     *
     * @param reporteRepository
     */
    @Autowired
    public ReporteServiceImpl(ReporteRepositoryImpl reporteRepository) {
        this.reporteRepository = reporteRepository;
    }

    /**
     * Películas con puntaje mayor
     *
     * @return lista de películas
     */
    public List<PeliculaReporteResponse> peliculasConPuntajeMayor() {
        return reporteRepository.peliculasConPuntajeMayor(10);
    }

    /**
     * numero de peliculas
     *
     * @return cantidad de peliculas
     */
    public Integer numeroPeliculas() {
        return reporteRepository.numeroPeliculasPorEstado(Pelicula.ACTIVO);
    }

}
