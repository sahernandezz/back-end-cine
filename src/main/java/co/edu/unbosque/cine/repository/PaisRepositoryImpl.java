package co.edu.unbosque.cine.repository;

import co.edu.unbosque.cine.model.Pais;
import co.edu.unbosque.cine.repository.jpa.PaisJpaRepositoryImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class PaisRepositoryImpl {
    /**
     * jpa repository pais
     */
    private PaisJpaRepositoryImpl paisJpa;

    /**
     * Constructor
     *
     * @param p pais jpa
     */
    @Autowired
    public PaisRepositoryImpl(PaisJpaRepositoryImpl p) {
        this.paisJpa = p;
    }

    /**
     * Metodo que permite obtener todos los paises activos
     *
     * @return lista de paises activos
     */
    public List<Pais> listaPaisesActivos() {
        return paisJpa.findAllByEstado(Pais.ACTIVO);
    }
}
