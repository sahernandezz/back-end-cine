package co.edu.unbosque.cine.service;

import co.edu.unbosque.cine.repository.HistorialRepositoryImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class HistorialUsuarioServiceImpl {

    /**
     * Inyección de dependencias
     */
    private HistorialRepositoryImpl historialRepositoryImpl;

    /**
     * Constructor
     *
     * @param historialRepositoryImpl inyección de dependencias
     */
    @Autowired
    public HistorialUsuarioServiceImpl(HistorialRepositoryImpl historialRepositoryImpl) {
        this.historialRepositoryImpl = historialRepositoryImpl;
    }
}
