package co.edu.unbosque.cine.service;

import co.edu.unbosque.cine.model.FacturaComida;
import co.edu.unbosque.cine.model.FacturaFuncion;
import co.edu.unbosque.cine.repository.FacturaComidaRepositoryImpl;
import co.edu.unbosque.cine.repository.FacturaFuncionRepositoryImpl;
import co.edu.unbosque.cine.repository.PeliculaRepositoryImpl;
import co.edu.unbosque.cine.repository.PuntajeUsuarioRepositoryImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClienteServiceImpl {

    /**
     * Inyección de dependencias
     */
    private PuntajeUsuarioRepositoryImpl puntajeRepository;

    /**
     * Inyección de dependencias
     */
    private FacturaComidaRepositoryImpl facturaComidaRepository;

    /**
     * Inyección de dependencias
     */
    private FacturaFuncionRepositoryImpl facturaFuncionRepository;


    /**
     * Constructor
     *
     * @param puntajeRepository        Inyección de dependencias puntaje
     * @param facturaComidaRepository  Inyección de dependencias factura comida
     * @param facturaFuncionRepository Inyección de dependencias factura funcion
     */
    @Autowired
    public ClienteServiceImpl(PuntajeUsuarioRepositoryImpl puntajeRepository, FacturaComidaRepositoryImpl facturaComidaRepository, FacturaFuncionRepositoryImpl facturaFuncionRepository) {
        this.puntajeRepository = puntajeRepository;
        this.facturaComidaRepository = facturaComidaRepository;
        this.facturaFuncionRepository = facturaFuncionRepository;
    }


    /**
     * Obtener los puntos de un usuario
     *
     * @param idUsuario id del usuario
     * @return puntos del usuario
     */
    public Integer puntosUsuario(final Integer idUsuario) {
        return this.puntajeRepository.puntosUsuario(idUsuario);
    }

    /**
     * Obtener las facturas de comida por usuario
     *
     * @param idUsuario id del usuario
     * @return lista de facturas
     */
    public List<FacturaComida> facturasComidaUsuario(final Integer idUsuario) {
        return this.facturaComidaRepository.facturasComidaPorCliente(idUsuario, FacturaComida.PAGADO);
    }

    /**
     * Obtener las facturas de funcion por usuario
     *
     * @param idUsuario id del usuario
     * @return lista de facturas
     */
    public List<FacturaFuncion> facturasFuncionUsuario(final Integer idUsuario) {
        return this.facturaFuncionRepository.facturasFuncionPorCliente(idUsuario, FacturaFuncion.PAGADO);
    }
}
