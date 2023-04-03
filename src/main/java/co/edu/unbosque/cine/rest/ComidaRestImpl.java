package co.edu.unbosque.cine.rest;

import co.edu.unbosque.cine.service.ComidaServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/comida/api/v1")
public class ComidaRestImpl {

    /**
     * Inyección de dependencias
     */
    private ComidaServiceImpl comidasService;

    /**
     * Constructor
     *
     * @param comidasService servicio comida
     */
    @Autowired
    public ComidaRestImpl(ComidaServiceImpl comidasService) {
        this.comidasService = comidasService;
    }


    /**
     * Método que permite obtener lista de comidas activas por pais
     *
     * @param idPais identificador del pais
     * @return lista de comidas activas por pais
     */
    @GetMapping("/lista_pais/{id_pais}")
    public ResponseEntity<?> listaComidasActivasPorPais(final @PathVariable("id_pais") Integer idPais) {
        ResponseEntity<?> response;
        try {
            response = ResponseEntity.ok(this.comidasService.listaDeComidasPorPais(idPais));
        } catch (Exception e) {
            response = ResponseEntity.internalServerError().body("Error al consultar las comidas por pais");
        }
        return response;
    }
}
