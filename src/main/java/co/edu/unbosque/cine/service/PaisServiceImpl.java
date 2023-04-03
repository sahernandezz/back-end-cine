package co.edu.unbosque.cine.service;

import co.edu.unbosque.cine.model.Pais;
import co.edu.unbosque.cine.repository.PaisRepositoryImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PaisServiceImpl {
    /**
     * Inyección de dependencias
     */
    private PaisRepositoryImpl paisRepository;

    /**
     * Constructor
     *
     * @param p PaisRepositoryImpl
     */
    @Autowired
    public PaisServiceImpl(PaisRepositoryImpl p) {
        this.paisRepository = p;
    }

    /**
     * Método que permite listar los paises activos
     *
     * @return Lista de paises activo
     */
    public List<Pais> listaPaisesActivos() {
        return this.paisRepository.listaPaisesActivos();
    }
}
