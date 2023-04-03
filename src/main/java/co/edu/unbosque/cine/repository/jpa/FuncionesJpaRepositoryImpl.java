package co.edu.unbosque.cine.repository.jpa;

import co.edu.unbosque.cine.model.Funcion;
import co.edu.unbosque.cine.model.FuncionSalaSilla;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface FuncionesJpaRepositoryImpl extends JpaRepository<Funcion, Integer> {

    /**
     * funciones por ciudad y pelicula
     *
     * @param idCiudad       id de la ciudad
     * @param estadoCiudad   estado de la ciudad
     * @param idPelicula     id de la pelicula
     * @param estadoPelicula estado de la pelicula
     * @param estadoFuncion  estado de la funcion
     * @return lista de funciones
     */
    @Query("SELECT funcion FROM Funcion funcion " +
            "WHERE funcion.multiplexPelicula.multiplex.ciudad.id = :id_ciudad " +
            "AND funcion.multiplexPelicula.multiplex.ciudad.estado = :estado_ciudad " +
            "AND funcion.multiplexPelicula.pelicula.id = :id_pelicula " +
            "AND funcion.multiplexPelicula.pelicula.estado = :estado_pelicula " +
            "AND funcion.estado= :estado_funcion " +
            "AND funcion.fechaInicio >=  CURRENT_DATE " +
            "ORDER BY funcion.fechaInicio, funcion.multiplexPelicula.multiplex.nombre"
    )
    List<Funcion> funcionesPorCiudadPelicula(final @Param("id_ciudad") Integer idCiudad, final @Param("estado_ciudad") String estadoCiudad, final @Param("id_pelicula") Integer idPelicula, final @Param("estado_pelicula") String estadoPelicula, final @Param("estado_funcion") String estadoFuncion);

    @Query("SELECT funcion_sala  FROM FuncionSalaSilla funcion_sala " +
            "WHERE funcion_sala.facturaFuncion.funcion.id = :id_funcion " +
            "AND funcion_sala.estado = :estado_silla"
    )
    List<FuncionSalaSilla> sillasPorFuncionPorEstado(final @Param("id_funcion") Integer idFuncion, final @Param("estado_silla") String estadoSilla);
}
