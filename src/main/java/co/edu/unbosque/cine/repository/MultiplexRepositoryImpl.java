package co.edu.unbosque.cine.repository;

import co.edu.unbosque.cine.model.Multiplex;
import co.edu.unbosque.cine.repository.jpa.MultiplexJpaRepositoryImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class MultiplexRepositoryImpl {

    /**
     * Inyección de dependencias
     */
    private MultiplexJpaRepositoryImpl multiplexJpaRepository;

    /**
     * Constructor
     *
     * @param multiplexJpaRepository inyección de dependencias
     */
    @Autowired
    public MultiplexRepositoryImpl(MultiplexJpaRepositoryImpl multiplexJpaRepository) {
        this.multiplexJpaRepository = multiplexJpaRepository;
    }

    /**
     * Método que permite listar todos los multiplex por ciudad y estado
     *
     * @param idCiudad identificador de la ciudad
     * @param estado   estado del multiplex
     * @return lista de multiplex
     */
    public List<Multiplex> listaMultiplexPorCiudadPorEstado(final Integer idCiudad, final String estado) {
        return multiplexJpaRepository.listaMultiplexPorCiudadPorEstado(idCiudad, estado);
    }
}
