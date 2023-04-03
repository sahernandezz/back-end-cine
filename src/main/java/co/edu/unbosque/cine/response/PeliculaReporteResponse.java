package co.edu.unbosque.cine.response;

import co.edu.unbosque.cine.model.Pelicula;
import lombok.Data;

@Data
public class PeliculaReporteResponse {
    private Integer puntaje;
    private Pelicula pelicula;
}
