package co.edu.unbosque.cine.rest;

import co.edu.unbosque.cine.service.TipoDocumentoServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Async;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping("/tipo_documento/api/v1")
public class TipoDocumentoRestImpl {
    /**
     * servicio tipo documento
     */
    private TipoDocumentoServiceImpl tipoDocumentoService;

    /**
     * constructor
     *
     * @param t servicio tipo documento
     */
    @Autowired
    public TipoDocumentoRestImpl(TipoDocumentoServiceImpl t) {
        this.tipoDocumentoService = t;
    }

    /**
     * metodo para listar los tipos de documento por pais
     *
     * @param idPais id del pais
     * @return lista de tipos de documento
     */
    @Async
    @GetMapping("/lista/{pais}")
    public CompletableFuture<ResponseEntity<?>> listaPorPais(final @PathVariable("pais") Integer idPais) {
        ResponseEntity<?> response;
        try {
            response = ResponseEntity.ok(this.tipoDocumentoService.listarPorPais(idPais));
        } catch (Exception e) {
            response = ResponseEntity.internalServerError().body("");
        }
        return CompletableFuture.completedFuture(response);
    }
}
