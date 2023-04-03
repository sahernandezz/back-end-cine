package co.edu.unbosque.cine.repository;

import co.edu.unbosque.cine.model.Ciudad;
import co.edu.unbosque.cine.repository.jpa.CiudadJpaRepositoryImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CiudadRepositoryImpl {
    /**
     * jpa repository ciudad
     */
    private CiudadJpaRepositoryImpl ciudadJpa;

    /**
     * constructor
     *
     * @param c jpa repository ciudad
     */
    @Autowired
    public CiudadRepositoryImpl(CiudadJpaRepositoryImpl c) {
        this.ciudadJpa = c;
    }

    /**
     * lista de ciudades activas por pais
     *
     * @param p pais
     * @param e estado
     * @return lista de ciudades activas por pais
     */
    public List<Ciudad> listaCiudadesPorPaisPorEstado(final Integer p, final String e) {
        return ciudadJpa.findAllByPaisIdAndEstadoOrderByNombreAsc(p, e);
    }
}
