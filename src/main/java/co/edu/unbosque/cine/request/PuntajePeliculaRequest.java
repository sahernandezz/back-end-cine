package co.edu.unbosque.cine.request;

import co.edu.unbosque.cine.model.Multiplex;
import co.edu.unbosque.cine.model.Pelicula;
import co.edu.unbosque.cine.model.Usuario;
import lombok.Data;

@Data
public class PuntajePeliculaRequest {
        private Integer id;
        private Pelicula pelicula;
        private Usuario usuario;
        private Integer puntaje;
        private Multiplex multiplex;
}
