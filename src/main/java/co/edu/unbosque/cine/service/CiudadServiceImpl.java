package co.edu.unbosque.cine.service;

import co.edu.unbosque.cine.model.Ciudad;
import co.edu.unbosque.cine.repository.CiudadRepositoryImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CiudadServiceImpl {

    /**
     * Inyección de dependencias
     */
    private CiudadRepositoryImpl ciudadRepository;

    /**
     * Constructor
     * @param c CiudadRepositoryImpl
     */
    @Autowired
    public CiudadServiceImpl(CiudadRepositoryImpl c) {
        this.ciudadRepository = c;
    }


    /**
     * metodo para listar las ciudades por pais
     * @param p pais
     * @return lista de ciudades
     */
    public List<Ciudad> listaCiudadesPorPaisActivos(final Integer p) {
        return ciudadRepository.listaCiudadesPorPaisPorEstado(p, Ciudad.ACTIVO);
    }
}
