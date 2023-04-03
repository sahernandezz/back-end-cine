package co.edu.unbosque.cine.repository.jpa;

import co.edu.unbosque.cine.model.Ciudad;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CiudadJpaRepositoryImpl extends JpaRepository<Ciudad, Integer> {

    /**
     * Busca todas las ciudades por estado de un pais
     *
     * @param id     id del pais de la ciudad
     * @param estado estado de la ciudad
     * @return lista de ciudades por estado de un pais
     */
    List<Ciudad> findAllByPaisIdAndEstadoOrderByNombreAsc(final Integer id, final String estado);
}
