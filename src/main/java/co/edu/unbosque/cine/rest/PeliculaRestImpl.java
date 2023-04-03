package co.edu.unbosque.cine.rest;

import co.edu.unbosque.cine.request.PuntajePeliculaRequest;
import co.edu.unbosque.cine.service.PeliculaServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Async;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping("/pelicula/api/v1")
public class PeliculaRestImpl {

    /**
     * lista de peliculas activas
     */
    private PeliculaServiceImpl peliculaService;

    /**
     * constructor
     *
     * @param p servicio pelicula
     */
    @Autowired
    public PeliculaRestImpl(PeliculaServiceImpl p) {
        this.peliculaService = p;
    }

    /**
     * lista de películas en cartelera por ciudad
     *
     * @param idCiudad ciudad
     * @return lista de películas en cartelera por ciudad
     */
    @Async
    @GetMapping("/cartelera/{id_ciudad}")
    public CompletableFuture<ResponseEntity<?>> getListaPeliculasEnCarteleraPorCiudad(final @PathVariable("id_ciudad") Integer idCiudad) {
        ResponseEntity<?> response;
        try {
            response = ResponseEntity.ok(this.peliculaService.listaPeliculasPorCiudadEnCartelera(idCiudad));
        } catch (Exception e) {
            response = ResponseEntity.internalServerError().body("");
        }
        return CompletableFuture.completedFuture(response);
    }

    /**
     * lista de películas próximamente por ciudad
     *
     * @param idCiudad ciudad
     * @return lista de películas
     */
    @Async
    @GetMapping("/proximamente/{id_ciudad}")
    public CompletableFuture<ResponseEntity<?>> getListaPeliculasProximamentePorCiudad(final @PathVariable("id_ciudad") Integer idCiudad) {
        ResponseEntity<?> response;
        try {
            response = ResponseEntity.ok(this.peliculaService.listaPeliculasPorCiudadProximamente(idCiudad));
        } catch (Exception e) {
            response = ResponseEntity.internalServerError().body("");
        }
        return CompletableFuture.completedFuture(response);
    }

    /**
     * Método para obtener lista de peliculas por titulo
     *
     * @param idCiudad ciudad
     * @param titulo   titulo pelicula
     * @return lista peliculas
     */
    @Async
    @GetMapping("/titulo/{id_ciudad}/{titulo}")
    public CompletableFuture<ResponseEntity<?>> getListaPeliculasPorCiudadPorTitulo(final @PathVariable("id_ciudad") Integer idCiudad, final @PathVariable("titulo") String titulo) {
        ResponseEntity<?> response;
        try {
            response = ResponseEntity.ok(this.peliculaService.listaPeliculasPorCiudadTitulo(idCiudad, titulo));
        } catch (Exception e) {
            response = ResponseEntity.internalServerError().body("");
        }
        return CompletableFuture.completedFuture(response);
    }

    /**
     * Método pa obtener el promedio de puntos por pelicula
     *
     * @param idPelicula id pelicula
     * @return promedio de puntos
     */
    @Async
    @GetMapping("/puntaje/{id_pelicula}")
    public CompletableFuture<ResponseEntity<?>> getPuntajePelicula(final @PathVariable("id_pelicula") Integer idPelicula) {
        ResponseEntity<?> response;
        try {
            response = ResponseEntity.ok(this.peliculaService.puntajePelicula(idPelicula));
        } catch (Exception e) {
            response = ResponseEntity.internalServerError().body("");
        }
        return CompletableFuture.completedFuture(response);
    }

    /**
     * Método para obtener el puntaje de una pelicula por usuario
     *
     * @param idPelicula id pelicula
     * @param idUsuario  id usuario
     * @return puntaje de la pelicula
     */
    @Async
    @GetMapping("/puntaje/{id_pelicula}/{id_usuario}")
    public CompletableFuture<ResponseEntity<?>> getPuntajePorPeliculaPorUsuario(final @PathVariable("id_pelicula") Integer idPelicula, final @PathVariable("id_usuario") Integer idUsuario) {
        ResponseEntity<?> response;
        try {
            response = ResponseEntity.ok(this.peliculaService.valoracionPeliculaCliente(idPelicula, idUsuario));
        } catch (Exception e) {
            response = ResponseEntity.internalServerError().body("");
        }
        return CompletableFuture.completedFuture(response);
    }

    /**
     * Método para guardar el puntaje de una pelicula por usuario
     *
     * @param peliculaPuntaje objeto pelicula puntaje
     * @return mensaje de guardado
     */
    @Async
    @PostMapping("/puntaje")
    public CompletableFuture<ResponseEntity<?>> guardarPuntajePelicula(final @RequestBody PuntajePeliculaRequest peliculaPuntaje) {
        ResponseEntity<?> response;
        try {
            response = ResponseEntity.ok(this.peliculaService.guardarPuntajePelicula(peliculaPuntaje));
        } catch (Exception e) {
            response = ResponseEntity.internalServerError().body("");
        }
        return CompletableFuture.completedFuture(response);
    }

}
