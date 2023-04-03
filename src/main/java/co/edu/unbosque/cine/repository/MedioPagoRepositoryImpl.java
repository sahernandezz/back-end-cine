package co.edu.unbosque.cine.repository;

import co.edu.unbosque.cine.model.MedioPago;
import co.edu.unbosque.cine.repository.jpa.MedioPagoJpaRepositoryImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class MedioPagoRepositoryImpl {

    /**
     * Inyección de dependencias
     */
    private MedioPagoJpaRepositoryImpl medioPagoJpa;

    /**
     * Constructor
     *
     * @param medioPagoJpa inyección de dependencias
     */
    @Autowired
    public MedioPagoRepositoryImpl(MedioPagoJpaRepositoryImpl medioPagoJpa) {
        this.medioPagoJpa = medioPagoJpa;
    }

    /**
     * Método que permite listar los medios de pago por pais
     *
     * @param idPais identificador del pais
     * @param estado estado del medio de pago
     * @return lista de medios de pago
     */
    public List<MedioPago> listaPorPaisPorEstado(final Integer idPais, final String estado) {
        return this.medioPagoJpa.listaPorPaisPorEstado(idPais, estado);
    }
}
