package co.edu.unbosque.cine.rest;

import co.edu.unbosque.cine.model.FacturaFuncion;
import co.edu.unbosque.cine.request.PagoFuncionRequest;
import co.edu.unbosque.cine.service.FuncionServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Async;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping("/funcion/api/v1")
public class FuncionRestImpl {

    /**
     * Inyección de dependencias
     */
    private FuncionServiceImpl funcionService;

    /**
     * Constructor
     *
     * @param funcionService servicio funcion
     */
    @Autowired
    public FuncionRestImpl(FuncionServiceImpl funcionService) {
        this.funcionService = funcionService;
    }

    /**
     * Método que permite consultar las funciones de una película
     *
     * @param idCiudad   identificador de la ciudad
     * @param idPelicula identificador de la película
     * @return lista de funciones
     */
    @Async
    @GetMapping("/funciones/{id_ciudad}/{id_pelicula}")
    public CompletableFuture<ResponseEntity<?>> listaFuncionesPorCiudadPelicula(final @PathVariable("id_ciudad") Integer idCiudad, final @PathVariable("id_pelicula") Integer idPelicula) {
        ResponseEntity<?> response;
        try {
            response = ResponseEntity.ok(this.funcionService.funcionesPorCiudadPelicula(idCiudad, idPelicula));
        } catch (Exception e) {
            response = ResponseEntity.internalServerError().body("");
        }
        return CompletableFuture.completedFuture(response);
    }

    /**
     * Método que permite consultar las sillas de una función
     *
     * @param idFuncion identificador de la función
     * @return lista de sillas
     */
    @Async
    @GetMapping("/sillas/{id_funcion}")
    public CompletableFuture<ResponseEntity<?>> listaFuncionesPorCiudadPelicula(final @PathVariable("id_funcion") Integer idFuncion) {
        ResponseEntity<?> response;
        try {
            response = ResponseEntity.ok(this.funcionService.sillasPorFuncion(idFuncion));
        } catch (Exception e) {
            response = ResponseEntity.internalServerError().body("");
        }
        return CompletableFuture.completedFuture(response);
    }

    /**
     * Método que permite guardar el pago de una función
     *
     * @param request datos del pago
     * @return mensaje de confirmación
     */
    @Async
    @PostMapping("/pago")
    public CompletableFuture<ResponseEntity<?>> pagoFuncion(final @RequestBody PagoFuncionRequest request) {
        ResponseEntity<?> response;
        try {
            Optional<FacturaFuncion> factura = this.funcionService.pagoComida(request);
            response = factura.isPresent() ? ResponseEntity.ok(factura.get().getPuntaje().getPuntaje()) : ResponseEntity.badRequest().build();
        } catch (Exception e) {
            response = ResponseEntity.internalServerError().body("");
        }
        return CompletableFuture.completedFuture(response);
    }
}
