package co.edu.unbosque.cine.repository;

import co.edu.unbosque.cine.model.Comida;
import co.edu.unbosque.cine.repository.jpa.ComidaJpaRepositoryImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ComidaRepositoryImpl {
    /**
     * Inyección de dependencias
     */
    private ComidaJpaRepositoryImpl comidaJpa;

    /**
     * constructor
     *
     * @param comidaJpa jpa repository
     */
    @Autowired
    public ComidaRepositoryImpl(ComidaJpaRepositoryImpl comidaJpa) {
        this.comidaJpa = comidaJpa;
    }

    /**
     * Método para listar las comidas
     *
     * @return lista de comidas
     */
    public List<Comida> listaDeComidasActivasPorPais(final Integer idPais) {
        return comidaJpa.findAllByEstadoAndPaisIdOrderByNombre(Comida.ACTIVO, idPais);
    }
}
