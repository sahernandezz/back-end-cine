package co.edu.unbosque.cine.repository.jpa;

import co.edu.unbosque.cine.model.Pais;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PaisJpaRepositoryImpl extends JpaRepository<Pais, Integer> {

    /**
     * lista de países por estado
     *
     * @param estado estado del pais
     * @return lista de países
     */
    List<Pais> findAllByEstado(final String estado);
}
