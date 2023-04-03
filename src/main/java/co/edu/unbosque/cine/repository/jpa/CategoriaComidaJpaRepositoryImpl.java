package co.edu.unbosque.cine.repository.jpa;

import co.edu.unbosque.cine.model.CategoriaComida;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CategoriaComidaJpaRepositoryImpl extends JpaRepository<CategoriaComida, Integer> {

    /**
     * Método que lista las categorias de comida por pais
     *
     * @param idPais          id del pais
     * @param estadoCategoria estado de la categoria de comida
     * @param estadoComida    estado de la comida
     * @return lista de categorias de comida
     */
    @Query(value = "select distinct comida.categoria from Comida comida " +
            "where comida.pais.id = :id_pais " +
            "and comida.pais.estado = :estado_pais " +
            "and comida.categoria.estado = :estado_categoria " +
            "and comida.estado = :estado_comida " +
            "order by comida.categoria.nombre"
    )
    List<CategoriaComida> listaPorPaisEstadoEstadoComida(final @Param("id_pais") Integer idPais, final @Param("estado_pais") String estadoPais, final @Param("estado_categoria") String estadoCategoria, final @Param("estado_comida") String estadoComida);
}
