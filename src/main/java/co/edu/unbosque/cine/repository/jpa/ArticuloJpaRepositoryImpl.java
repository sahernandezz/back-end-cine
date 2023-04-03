package co.edu.unbosque.cine.repository.jpa;

import co.edu.unbosque.cine.model.Articulo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ArticuloJpaRepositoryImpl extends JpaRepository<Articulo, Integer> {

    /**
     * Buscar articulo por codigo, pais y estado
     *
     * @param codigo codigo del articulo
     * @param idPais id del pais
     * @param estado estado del articulo
     * @return articulo
     */
    @Query("SELECT articulo FROM Articulo articulo" +
            " WHERE articulo.pais.id = :id_pais " +
            " AND articulo.estado = :estado " +
            " AND articulo.codigo = :codigo_articulo"
    )
    Articulo buscarPorCodigoPorPaisPorEstado(final @Param("codigo_articulo") Integer codigo,
                                             final @Param("id_pais") Integer idPais,
                                             final @Param("estado") String estado);
}
