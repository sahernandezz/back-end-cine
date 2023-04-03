package co.edu.unbosque.cine.rest;

import co.edu.unbosque.cine.service.CiudadServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Async;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping("/ciudad/api/v1")
public class CiudadRestImpl {
    /**
     * ciudadService servicio ciudad
     */
    private CiudadServiceImpl ciudadService;

    /**
     * constructor
     *
     * @param c servicio ciudad
     */
    @Autowired
    CiudadRestImpl(CiudadServiceImpl c) {
        this.ciudadService = c;
    }


    /**
     * lista de ciudades activas por pais
     *
     * @param p pais
     * @return lista de ciudades activas por pais
     */
    @Async
    @GetMapping("/lista_pais/{id_pais}")
    public CompletableFuture<ResponseEntity<?>> listaCiudadesPorPaisActivos(final @PathVariable("id_pais") Integer p) {
        ResponseEntity<?> response;
        try {
            response = ResponseEntity.ok(this.ciudadService.listaCiudadesPorPaisActivos(p));
        } catch (Exception e) {
            //TODO: handle exception
            response = ResponseEntity.internalServerError().body("Error al consultar ciudades por pais");
        }
        return CompletableFuture.completedFuture(response);
    }
}
