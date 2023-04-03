package co.edu.unbosque.cine.repository.jpa;

import co.edu.unbosque.cine.model.Pelicula;
import co.edu.unbosque.cine.model.PeliculaPuntaje;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PeliculaPuntajeJpaRepositoryImpl extends JpaRepository<PeliculaPuntaje, Integer> {
    /**
     * puntaje por película calificada por un usuario
     *
     * @param idPelicula id de la película
     * @param idUsuario  id del usuario
     * @return puntaje de la película
     */
    @Query(value = "select pelicula_puntaje from PeliculaPuntaje pelicula_puntaje " +
            "where pelicula_puntaje.pelicula.id = :id_pelicula " +
            "and pelicula_puntaje.usuario.id = :id_usuario"
    )
    PeliculaPuntaje puntajePorPeliculaPorUsuario(final @Param("id_pelicula") Integer idPelicula, final @Param("id_usuario") Integer idUsuario);

    @Query(value = "select pelicula_puntaje.pelicula from PeliculaPuntaje pelicula_puntaje " +
            "group by pelicula_puntaje.pelicula " +
            "order by avg (pelicula_puntaje.puntaje)"
    )
    List<Pelicula> peliculasConPuntajeMayor();
}
