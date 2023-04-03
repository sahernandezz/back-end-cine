package co.edu.unbosque.cine.repository;

import co.edu.unbosque.cine.model.HistorialUsuario;
import co.edu.unbosque.cine.repository.jpa.HistorialUsuarioJpaRepositoryImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class HistorialRepositoryImpl {

    /**
     * Inyección de dependencias
     */
    private HistorialUsuarioJpaRepositoryImpl historialUsuarioJpa;

    /**
     * Constructor
     *
     * @param historialUsuarioJpa - Inyección de dependencias
     */
    @Autowired
    public HistorialRepositoryImpl(HistorialUsuarioJpaRepositoryImpl historialUsuarioJpa) {
        this.historialUsuarioJpa = historialUsuarioJpa;
    }

    /**
     * Método que permite guardar un registro en la tabla historial_usuario
     *
     * @param historialUsuario - historialUsuario
     */
    public void guardarHistorial(final HistorialUsuario historialUsuario) {
        historialUsuarioJpa.save(historialUsuario);
    }
}
