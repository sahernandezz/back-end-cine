package co.edu.unbosque.cine.repository.jpa;

import co.edu.unbosque.cine.model.Rol;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RolJpaRepository extends JpaRepository<Rol, Integer> {
    /**
     * Busca todos los roles por estado
     * @param estado estado del rol
     * @return lista de roles por estado
     */
    List<Rol> findAllByEstado(final String estado);
}
