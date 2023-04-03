package co.edu.unbosque.cine.repository.jpa;

import co.edu.unbosque.cine.model.Multiplex;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface MultiplexJpaRepositoryImpl extends JpaRepository<Multiplex, Integer> {

    /**
     * Consulta que lista los multiplex por ciudad y estado
     *
     * @param idCiudad id de la ciudad
     * @param estado   estado del multiplex
     * @return lista de multiplex
     */
    @Query("SELECT multiplex FROM Multiplex multiplex " +
            "WHERE multiplex.ciudad.id = :id_ciudad " +
            "AND multiplex.estado = :estado_multiplex")
    List<Multiplex> listaMultiplexPorCiudadPorEstado(final @Param("id_ciudad") Integer idCiudad, final @Param("estado_multiplex") String estado);
}
