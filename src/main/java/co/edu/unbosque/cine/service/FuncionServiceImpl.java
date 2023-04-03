package co.edu.unbosque.cine.service;

import co.edu.unbosque.cine.model.*;
import co.edu.unbosque.cine.repository.*;
import co.edu.unbosque.cine.request.PagoFuncionRequest;
import co.edu.unbosque.cine.request.SillaRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.*;

@Service
public class FuncionServiceImpl {

    /**
     * Inyección de dependencias
     */
    private FuncionRepositoryImpl funcionRepository;

    /**
     * Inyección de dependencias
     */
    private FacturaFuncionRepositoryImpl facturaFuncionRepository;

    /**
     * Inyección de dependencias
     */
    private UsuarioRepositoryImpl usuarioRepository;

    /**
     * Inyección de dependencias
     */
    private ArticuloRepositoryImpl articuloRepository;

    /**
     * Inyección de dependencias
     */
    private PuntajeUsuarioRepositoryImpl puntajeUsuarioRepository;

    /**
     * Inyección de dependencias
     */
    private PagoFuncionRepositoryImpl pagoFuncionRepository;

    /**
     * Inyección de dependencias
     */
    private FuncionSalaSillaRepositoryImpl funcionSalaSillaRepository;

    /**
     * Constructor
     *
     * @param funcionRepository        inyección de dependencias
     * @param facturaFuncionRepository inyección de dependencias
     * @param usuarioRepository        inyección de dependencias
     * @param articuloRepository       inyección de dependencias
     * @param puntajeUsuarioRepository inyección de dependencias
     * @param pagoFuncionRepository    inyección de dependencias
     */
    @Autowired
    public FuncionServiceImpl(FuncionRepositoryImpl funcionRepository, FacturaFuncionRepositoryImpl facturaFuncionRepository, UsuarioRepositoryImpl usuarioRepository, ArticuloRepositoryImpl articuloRepository, PuntajeUsuarioRepositoryImpl puntajeUsuarioRepository, PagoFuncionRepositoryImpl pagoFuncionRepository, FuncionSalaSillaRepositoryImpl funcionSalaSillaRepository) {
        this.funcionRepository = funcionRepository;
        this.facturaFuncionRepository = facturaFuncionRepository;
        this.usuarioRepository = usuarioRepository;
        this.articuloRepository = articuloRepository;
        this.puntajeUsuarioRepository = puntajeUsuarioRepository;
        this.pagoFuncionRepository = pagoFuncionRepository;
        this.funcionSalaSillaRepository = funcionSalaSillaRepository;
    }

    /**
     * funciones por ciudad y pelicula
     *
     * @param idCiudad   id de la ciudad
     * @param IdPelicula id de la pelicula
     * @return lista de funciones
     */
    public List<Funcion> funcionesPorCiudadPelicula(final Integer idCiudad, final Integer IdPelicula) {
        return this.funcionRepository.funcionesPorCiudadPelicula(idCiudad, Ciudad.ACTIVO, IdPelicula, Pelicula.ACTIVO, Funcion.ACTIVO);
    }

    /**
     * sillas por funcion
     *
     * @param idFuncion id de la funcion
     * @return lista de sillas
     */
    public List<FuncionSalaSilla> sillasPorFuncion(final Integer idFuncion) {
        return this.funcionRepository.sillasPorFuncionPorEstado(idFuncion, FuncionSalaSilla.PAGO);
    }

    /**
     * sillas por funcion
     *
     * @param pagoFuncionRequest datos de la compra
     * @return lista de sillas
     */
    @Transactional
    public Optional<FacturaFuncion> pagoComida(final PagoFuncionRequest pagoFuncionRequest) {
        List<FuncionSalaSilla> listaSillas = new ArrayList<>(Collections.emptyList());
        Optional<Usuario> usurio = this.usuarioRepository.buscarPorId(pagoFuncionRequest.getIdUsuario());
        Optional<Funcion> funcion = this.funcionRepository.buscarPorId(pagoFuncionRequest.getIdFuncion());
        Optional<Articulo> articulo = this.articuloRepository.buscarPorCodigoPorPaisPorEstado(1001, funcion.get().getMultiplexTarifa().getMultiplex().getCiudad().getPais().getId(), Articulo.ACTIVO);
        Float valor = this.calcularValor(pagoFuncionRequest.getSillas().get(0).getTipo().toUpperCase(), funcion.get(), articulo.get(), pagoFuncionRequest.getCantidadSillas());
        Optional<FacturaFuncion> factura = this.facturaFuncionRepository.guardarFactura(crearFactura(usurio.get(), funcion.get(), articulo.get(), valor));
        for (SillaRequest silla : pagoFuncionRequest.getSillas()) {
            listaSillas.add(this.crearFuncionSalaSilla(silla.getFila(), silla.getColumna(), silla.getTipo(), factura.get()));
        }
        Optional<PagoFuncion> pagoFuncion = this.pagoFuncionRepository.guardarPago(this.crearPagoFuncion(factura.get(), valor, pagoFuncionRequest.getMedioPago()));
        List<FuncionSalaSilla> funcionSalaSillas = pagoFuncion.isPresent() ? this.funcionSalaSillaRepository.guardarTodasLasSillas(listaSillas) : Collections.emptyList();
        return funcionSalaSillas.size() > 0 ? factura : Optional.empty();
    }

    /**
     * crear factura
     *
     * @param usuario  usuario
     * @param funcion  funcion
     * @param articulo articulo
     * @param valor    valor
     * @return factura
     */
    @Transactional
    FacturaFuncion crearFactura(final Usuario usuario, final Funcion funcion, final Articulo articulo, final Float valor) {
        Date fecha = new Date();
        Optional<PuntajeUsuario> puntajeUsuario = this.puntajeUsuarioRepository.guardarPuntaje(this.crearPuntajeUsuario(usuario, fecha));
        FacturaFuncion facturaFuncion = new FacturaFuncion();
        facturaFuncion.setUsuario(usuario);
        facturaFuncion.setFuncion(funcion);
        facturaFuncion.setFecha(fecha);
        facturaFuncion.setPuntaje(puntajeUsuario.get());
        facturaFuncion.setArticulo(articulo);
        facturaFuncion.setValor(valor);
        facturaFuncion.setEstado(FacturaFuncion.PAGADO);
        return facturaFuncion;
    }

    /**
     * crear puntaje usuario
     *
     * @param usuario usuario
     * @param fecha   fecha
     * @return puntaje usuario
     */
    private PuntajeUsuario crearPuntajeUsuario(final Usuario usuario, final Date fecha) {
        PuntajeUsuario puntajeUsuario = new PuntajeUsuario();
        puntajeUsuario.setUsuario(usuario);
        puntajeUsuario.setFecha(fecha);
        puntajeUsuario.setPuntaje(10);
        puntajeUsuario.setEstado(PuntajeUsuario.DESICNADO);
        return puntajeUsuario;
    }

    /**
     * crear pago funcion
     *
     * @param facturaFuncion factura
     * @param valor          valor
     * @param medioPago      medio de pago
     * @return pago funcion
     */
    private PagoFuncion crearPagoFuncion(final FacturaFuncion facturaFuncion, final Float valor, final MedioPago medioPago) {
        PagoFuncion pagoFuncion = new PagoFuncion();
        pagoFuncion.setFacturaFuncion(facturaFuncion);
        pagoFuncion.setPago(valor);
        pagoFuncion.setMedioPago(medioPago);
        pagoFuncion.setFecha(new Date());
        pagoFuncion.setEstado(PagoFuncion.PAGO);
        return pagoFuncion;
    }

    /**
     * crear funcion sala silla
     *
     * @param fila    fila
     * @param columna columna
     * @param tipo    tipo de silla
     * @return funcion sala silla
     */
    private FuncionSalaSilla crearFuncionSalaSilla(final String fila, final String columna, final String tipo, final FacturaFuncion facturaFuncion) {
        FuncionSalaSilla funcionSalaSilla = new FuncionSalaSilla();
        funcionSalaSilla.setTipoSilla(tipo.toUpperCase());
        funcionSalaSilla.setFila(fila);
        funcionSalaSilla.setFuncion(facturaFuncion.getFuncion());
        funcionSalaSilla.setFacturaFuncion(facturaFuncion);
        funcionSalaSilla.setColumna(columna);
        funcionSalaSilla.setEstado(FuncionSalaSilla.PAGO);
        return funcionSalaSilla;
    }

    /**
     * calcular valor
     *
     * @param tipoSilla tipo de silla
     * @param funcion   funcion
     * @param articulo  articulo
     * @return valor calculado
     */
    private Float calcularValor(final String tipoSilla, final Funcion funcion, final Articulo articulo, final Integer cantidad) {
        return tipoSilla.equals("G")
                ? (funcion.getMultiplexTarifa().getValorGeneral() + funcion.getMultiplexTarifa().getValorGeneral() * articulo.getPorcentajeImpuesto()) * cantidad
                : (funcion.getMultiplexTarifa().getValorPreferencial() + funcion.getMultiplexTarifa().getValorPreferencial() * articulo.getPorcentajeImpuesto()) * cantidad;
    }
}
