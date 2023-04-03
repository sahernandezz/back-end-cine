package co.edu.unbosque.cine.rest;

import co.edu.unbosque.cine.service.MultiplexServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Async;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping("/multiplex/api/v1")
public class MultiplexRestImpl {

    /**
     * Inyección de dependencias
     */
    private MultiplexServiceImpl multiplexService;

    /**
     * Constructor
     *
     * @param multiplexService inyección de dependencias
     */
    @Autowired
    public MultiplexRestImpl(MultiplexServiceImpl multiplexService) {
        this.multiplexService = multiplexService;
    }

    /**
     * Método que permite obtener todos los multiplex por ciudad
     *
     * @param idCiudad identificador de la ciudad
     * @return lista de multiplex
     */
    @Async
    @GetMapping("/lista/{id_ciudad}")
    public CompletableFuture<ResponseEntity<?>> listaMultiplexPorCiudad(final @PathVariable("id_ciudad") Integer idCiudad) {
        ResponseEntity<?> response;
        try {
            response = ResponseEntity.ok(this.multiplexService.listaMultiplexPorCiudad(idCiudad));
        } catch (Exception e) {
            response = ResponseEntity.internalServerError().body("");
        }
        return CompletableFuture.completedFuture(response);
    }
}
