package co.edu.unbosque.cine.repository;

import co.edu.unbosque.cine.model.FacturaComida;
import co.edu.unbosque.cine.repository.jpa.FacturaComidaJpaRepositoryImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class FacturaComidaRepositoryImpl {

    /**
     * Inyección de dependencias
     */
    private FacturaComidaJpaRepositoryImpl FacturaComidaJpa;

    /**
     * Constructor
     *
     * @param FacturaComidaJpa Inyección de dependencias
     */
    @Autowired
    public FacturaComidaRepositoryImpl(FacturaComidaJpaRepositoryImpl FacturaComidaJpa) {
        this.FacturaComidaJpa = FacturaComidaJpa;
    }

    /**
     * Consulta que permite listar las facturas de comida por usuario
     *
     * @param idUsuario     Identificador del usuario
     * @param estadoFactura Estado de la factura
     * @return Lista de facturas de comida
     */
    public List<FacturaComida> facturasComidaPorCliente(final Integer idUsuario, final String estadoFactura) {
        return FacturaComidaJpa.facturaPorCliente(idUsuario, estadoFactura);
    }
}
