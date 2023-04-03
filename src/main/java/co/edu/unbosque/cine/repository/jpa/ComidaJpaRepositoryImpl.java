package co.edu.unbosque.cine.repository.jpa;

import co.edu.unbosque.cine.model.Comida;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ComidaJpaRepositoryImpl extends JpaRepository<Comida, Integer> {

    List<Comida> findAllByEstadoAndPaisIdOrderByNombre(final String estado, final Integer paisId);

}
