package co.edu.unbosque.cine.rest;

import co.edu.unbosque.cine.service.MedioPagoServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Async;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping("/medio_pago/api/v1")
public class MedioPagoRestImpl {

    /**
     * Inyección de dependencias
     */
    private MedioPagoServiceImpl medioPagoService;

    /**
     * Constructor
     *
     * @param medioPagoService inyección de dependencias
     */
    @Autowired
    public MedioPagoRestImpl(MedioPagoServiceImpl medioPagoService) {
        this.medioPagoService = medioPagoService;
    }

    /**
     * Lista de medios de pago por pais
     *
     * @param idPais id del pais
     * @return lista de medios de pago
     */
    @Async
    @GetMapping("/lista/{id_pais}")
    public CompletableFuture<ResponseEntity<?>> listaMedioPagoPorPais(final @PathVariable("id_pais") Integer idPais) {
        ResponseEntity<?> response;
        try {
            response = ResponseEntity.ok(this.medioPagoService.listaPorPais(idPais));
        } catch (Exception e) {
            response = ResponseEntity.internalServerError().body("");
        }
        return CompletableFuture.completedFuture(response);
    }
}
