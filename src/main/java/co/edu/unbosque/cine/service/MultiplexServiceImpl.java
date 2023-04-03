package co.edu.unbosque.cine.service;

import co.edu.unbosque.cine.model.Multiplex;
import co.edu.unbosque.cine.repository.MultiplexRepositoryImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MultiplexServiceImpl {

    /**
     * Inyección de dependencias
     */
    private MultiplexRepositoryImpl multiplexRepository;

    /**
     * Constructor
     *
     * @param multiplexRepository inyección de dependencias
     */
    @Autowired
    public MultiplexServiceImpl(MultiplexRepositoryImpl multiplexRepository) {
        this.multiplexRepository = multiplexRepository;
    }

    /**
     * Método que permite listar todos los multiplex por ciudad
     *
     * @param idCiudad identificador de la ciudad
     * @return lista de multiplex
     */
    public List<Multiplex> listaMultiplexPorCiudad(final Integer idCiudad) {
        return multiplexRepository.listaMultiplexPorCiudadPorEstado(idCiudad, Multiplex.ACTIVO);
    }
}
