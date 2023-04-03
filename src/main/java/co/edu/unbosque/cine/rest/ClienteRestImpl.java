package co.edu.unbosque.cine.rest;

import co.edu.unbosque.cine.service.ClienteServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Async;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping("/cliente/api/v1")
public class ClienteRestImpl {

    /**
     * Servicio de cliente
     */
    private ClienteServiceImpl clienteService;

    /**
     * Constructor
     *
     * @param clienteService
     */
    @Autowired
    public ClienteRestImpl(ClienteServiceImpl clienteService) {
        this.clienteService = clienteService;
    }


    /**
     * método que permite obtener los puntos de un usuario
     *
     * @param idUsuario id del usuario
     * @return puntos del usuario
     */
    @Async
    @GetMapping("/puntos/{id_usuario}")
    public CompletableFuture<ResponseEntity<?>> puntosPorCliente(final @PathVariable("id_usuario") Integer idUsuario) {
        ResponseEntity<?> response;
        try {
            response = ResponseEntity.ok(this.clienteService.puntosUsuario(idUsuario));
        } catch (Exception e) {
            response = ResponseEntity.internalServerError().body("Error al consultar los puntos del usuario");
        }
        return CompletableFuture.completedFuture(response);
    }

    /**
     * método que permite obtener las facturas de comida de un usuario
     *
     * @param idUsuario id del usuario
     * @return facturas del usuario
     */
    @Async
    @GetMapping("/facturas/comida/{id_usuario}")
    public CompletableFuture<ResponseEntity<?>> facturasComidaPorCliente(final @PathVariable("id_usuario") Integer idUsuario) {
        ResponseEntity<?> response;
        try {
            response = ResponseEntity.ok(this.clienteService.facturasComidaUsuario(idUsuario));
        } catch (Exception e) {
            response = ResponseEntity.internalServerError().body("Error al consultar las facturas de comida del usuario");
        }
        return CompletableFuture.completedFuture(response);
    }

    /**
     * método que permite obtener las facturas de funcion de un usuario
     *
     * @param idUsuario id del usuario
     * @return facturas del usuario
     */
    @Async
    @GetMapping("/facturas/funcion/{id_usuario}")
    public CompletableFuture<ResponseEntity<?>> facturasFuncionPorCliente(final @PathVariable("id_usuario") Integer idUsuario) {
        ResponseEntity<?> response;
        try {
            response = ResponseEntity.ok(this.clienteService.facturasFuncionUsuario(idUsuario));
        } catch (Exception e) {
            response = ResponseEntity.internalServerError().body("Error al consultar las facturas de funcion del usuario");
        }
        return CompletableFuture.completedFuture(response);
    }
}
