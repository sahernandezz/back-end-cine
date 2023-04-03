package co.edu.unbosque.cine.rest;

import co.edu.unbosque.cine.service.PaisServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Async;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping("/pais/api/v1")
public class PaisRestImpl {
    /**
     * servicio pais
     */
    private PaisServiceImpl paisService;

    /**
     * constructor
     *
     * @param p servicio pais
     */
    @Autowired
    public PaisRestImpl(PaisServiceImpl p) {
        this.paisService = p;
    }

    /**
     * lista de paises activos
     *
     * @return lista de paises activos
     */
    @Async
    @GetMapping("/lista")
    public CompletableFuture<ResponseEntity<?>> listaPaisesActivos() {
        ResponseEntity<?> response;
        try {
            response = ResponseEntity.ok(this.paisService.listaPaisesActivos());
        } catch (Exception e) {
            response = ResponseEntity.internalServerError().body("");
        }
        return CompletableFuture.completedFuture(response);
    }
}
