package co.edu.unbosque.cine.rest;

import co.edu.unbosque.cine.service.CategoriaComidaServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Async;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping("/categoria_comida/api/v1")
public class CategoriaComidaRestImpl {
    /**
     * Inyección de dependencias
     */
    private CategoriaComidaServiceImpl categoriaComidaService;

    /**
     * Constructor
     *
     * @param c servicio
     */
    @Autowired
    public CategoriaComidaRestImpl(CategoriaComidaServiceImpl c) {
        this.categoriaComidaService = c;
    }


    /**
     * Método que permite obtener todas las categorías de comida activas por pais
     *
     * @param idPais id del pais
     * @return lista de categorías de comida activas por pais
     */
    @Async
    @GetMapping("/lista_pais/{id_pais}")
    public CompletableFuture<ResponseEntity<?>> listaCategoriasActivasPorPais(final @PathVariable("id_pais") Integer idPais) {
        ResponseEntity<?> response;
        try {
            response = ResponseEntity.ok(this.categoriaComidaService.listaPorPais(idPais));
        } catch (Exception e) {
            response = ResponseEntity.internalServerError().body("Error al obtener las categorías de comida");
        }
        return CompletableFuture.completedFuture(response);
    }
}
