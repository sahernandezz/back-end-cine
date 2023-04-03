package co.edu.unbosque.cine.rest;

import co.edu.unbosque.cine.service.ReporteServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Async;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping("/reporte/api/v1")
public class ReporteRestImpl {

    /**
     * Inyección de dependencias
     */
    private ReporteServiceImpl reporteService;

    /**
     * Constructor
     *
     * @param reporteService servicio reporte
     */
    @Autowired
    public ReporteRestImpl(ReporteServiceImpl reporteService) {
        this.reporteService = reporteService;
    }

    /**
     * Películas con puntaje mayor
     *
     * @return lista de películas
     */
    @Async
    @GetMapping("/pelicula/lista/puntaje")
    public CompletableFuture<ResponseEntity<?>> listaPorPais() {
        ResponseEntity<?> response;
        try {
            response = ResponseEntity.ok(this.reporteService.peliculasConPuntajeMayor());
        } catch (Exception e) {
            response = ResponseEntity.internalServerError().body("");
        }
        return CompletableFuture.completedFuture(response);
    }

    @Async
    @GetMapping("/pelicula/cantidad")
    public CompletableFuture<ResponseEntity<?>> cantidadPeliculas() {
        ResponseEntity<?> response;
        try {
            response = ResponseEntity.ok(this.reporteService.numeroPeliculas());
        } catch (Exception e) {
            response = ResponseEntity.internalServerError().body("");
        }
        return CompletableFuture.completedFuture(response);
    }
}
