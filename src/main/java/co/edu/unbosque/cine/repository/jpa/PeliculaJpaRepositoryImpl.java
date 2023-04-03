package co.edu.unbosque.cine.repository.jpa;

import co.edu.unbosque.cine.model.Pelicula;
import co.edu.unbosque.cine.model.PeliculaPuntaje;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PeliculaJpaRepositoryImpl extends JpaRepository<Pelicula, Integer> {

    /**
     * lista de películas por ciudad y estado
     *
     * @param idCiudad                id de la ciudad
     * @param estadoPelicula          estado de la película
     * @param estadoPeliculaMultiplex estado de la película
     * @param estadoMultiplex         estado del multiplex
     * @return lista de películas
     */
    @Query(value = "select distinct multiplex_pelicula.pelicula from MultiplexPelicula multiplex_pelicula " +
            "where multiplex_pelicula.multiplex.ciudad.id = :id_ciudad " +
            "and multiplex_pelicula.multiplex.ciudad.estado = :estado_ciudad " +
            "and multiplex_pelicula.multiplex.estado = :estado_multiplex " +
            "and multiplex_pelicula.pelicula.estado = :estado_pelicula " +
            "and multiplex_pelicula.estado = :estado_pelicula_multiplex " +
            "order by multiplex_pelicula.pelicula.titulo"
    )
    List<Pelicula> listaPorCiudadEstadoMultiplex(final @Param("id_ciudad") Integer idCiudad,
                                                 final @Param("estado_ciudad") String estadoCiudad,
                                                 final @Param("estado_pelicula") String estadoPelicula,
                                                 final @Param("estado_pelicula_multiplex") String estadoPeliculaMultiplex,
                                                 final @Param("estado_multiplex") String estadoMultiplex);

    /**
     * lista de películas por ciudad, estado y titulo
     *
     * @param idCiudad                  id de la ciudad
     * @param estadoPelicula            estado de la película
     * @param estadoMultiplex           estado del multiplex
     * @param tituloPelicula            titulo de la película
     * @param estadoPeliculaMultiplex_1 estado del multiplex v1
     * @param estadoPeliculaMultiplex_2 estado del multiplex v2
     * @return lista de películas
     */
    @Query(value = "select distinct multiplex_pelicula.pelicula from MultiplexPelicula multiplex_pelicula " +
            "where multiplex_pelicula.multiplex.ciudad.id = :id_ciudad " +
            "and multiplex_pelicula.multiplex.ciudad.estado = :estado_ciudad " +
            "and multiplex_pelicula.multiplex.estado = :estado_multiplex " +
            "and multiplex_pelicula.pelicula.titulo like %:titulo_pelicula% " +
            "and multiplex_pelicula.pelicula.estado = :estado_pelicula " +
            "and multiplex_pelicula.estado = :estado_pelicula_multiplex_1 " +
            "or " +
            "multiplex_pelicula.multiplex.ciudad.id = :id_ciudad " +
            "and multiplex_pelicula.multiplex.ciudad.estado = :estado_ciudad " +
            "and multiplex_pelicula.multiplex.estado = :estado_multiplex " +
            "and multiplex_pelicula.pelicula.titulo like %:titulo_pelicula% " +
            "and multiplex_pelicula.pelicula.estado = :estado_pelicula " +
            "and multiplex_pelicula.estado = :estado_pelicula_multiplex_2 " +
            "order by multiplex_pelicula.pelicula.titulo"
    )
    List<Pelicula> listaPorCiudadEstadoPorTituloMultiplex(final @Param("id_ciudad") Integer idCiudad,
                                                          final @Param("estado_ciudad") String estadoCiudad,
                                                          final @Param("titulo_pelicula") String tituloPelicula,
                                                          final @Param("estado_pelicula") String estadoPelicula,
                                                          final @Param("estado_pelicula_multiplex_1") String estadoPeliculaMultiplex_1,
                                                          final @Param("estado_pelicula_multiplex_2") String estadoPeliculaMultiplex_2,
                                                          final @Param("estado_multiplex") String estadoMultiplex);

    /**
     * promedio de puntaje por película
     *
     * @param idPelicula id de la película
     * @return promedio de puntaje
     */
    @Query(value = "select avg(pelicula_puntaje.puntaje) from PeliculaPuntaje pelicula_puntaje " +
            "where pelicula_puntaje.pelicula.id = :id_pelicula"
    )
    Integer puntajePorPelicula(final @Param("id_pelicula") Integer idPelicula);

    /**
     * cantidad de peliculas por estado
     *
     * @param estado estado de la película
     * @return cantidad de peliculas
     */
    @Query(value = "select count(pelicula) from Pelicula pelicula " +
            "where pelicula.estado = :estado_pelicula"
    )
    Integer cantidadPeliculas(final @Param("estado_pelicula") String estado);
}
