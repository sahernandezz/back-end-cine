package co.edu.unbosque.cine.service;

import co.edu.unbosque.cine.model.MedioPago;
import co.edu.unbosque.cine.repository.MedioPagoRepositoryImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MedioPagoServiceImpl {

    /**
     * Inyección de dependencias
     */
    private MedioPagoRepositoryImpl medioPagoRepository;

    /**
     * Constructor
     *
     * @param medioPagoRepository inyección de dependencias
     */
    @Autowired
    public MedioPagoServiceImpl(MedioPagoRepositoryImpl medioPagoRepository) {
        this.medioPagoRepository = medioPagoRepository;
    }

    /**
     * Método que permite listar los medios de pago por pais
     *
     * @param idPais identificador del pais
     * @return lista de medios de pago
     */
    public List<MedioPago> listaPorPais(final Integer idPais) {
        return this.medioPagoRepository.listaPorPaisPorEstado(idPais, MedioPago.PAGO_ONLINE);
    }
}
