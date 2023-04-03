package co.edu.unbosque.cine.repository;

import co.edu.unbosque.cine.model.FacturaFuncion;
import co.edu.unbosque.cine.repository.jpa.FacturaFuncionJpaRepositoryImpl;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Repository
public class FacturaFuncionRepositoryImpl {

    /**
     * Inyección de dependencias
     */
    private FacturaFuncionJpaRepositoryImpl facturaFuncionJpaRepository;

    /**
     * Constructor
     *
     * @param facturaFuncionJpaRepository Inyección de dependencias
     */
    public FacturaFuncionRepositoryImpl(FacturaFuncionJpaRepositoryImpl facturaFuncionJpaRepository) {
        this.facturaFuncionJpaRepository = facturaFuncionJpaRepository;
    }

    /**
     * Consulta que permite listar las facturas de funcion por usuario
     *
     * @param idUsuario     Identificador del usuario
     * @param estadoFactura Estado de la factura
     * @return Lista de facturas de funcion
     */
    public List<FacturaFuncion> facturasFuncionPorCliente(final Integer idUsuario, final String estadoFactura) {
        return facturaFuncionJpaRepository.facturaPorCliente(idUsuario, estadoFactura);
    }

    @Transactional
    public Optional<FacturaFuncion> guardarFactura(final FacturaFuncion facturaFuncion) {
        return Optional.of(facturaFuncionJpaRepository.save(facturaFuncion));
    }
}
