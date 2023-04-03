package co.edu.unbosque.cine.rest;

import co.edu.unbosque.cine.model.Usuario;
import co.edu.unbosque.cine.service.UsuarioServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Async;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping("/usuario/api/v2")
public class UsuarioRestV2Impl {
    /**
     * servicio usuario
     */
    private UsuarioServiceImpl usuarioService;

    /**
     * constructor
     *
     * @param u servicio usuario
     */
    @Autowired
    public UsuarioRestV2Impl(UsuarioServiceImpl u) {
        this.usuarioService = u;
    }

    /**
     * lista de usuarios
     *
     * @return lista de usuarios
     */
    @Async
    @GetMapping(value = "/lista")
    public CompletableFuture<ResponseEntity<?>> listaUsuarios() {
        ResponseEntity<?> response;
        try {
            response = ResponseEntity.ok(this.usuarioService.listarUsuarios());
        } catch (Exception e) {
            response = ResponseEntity.internalServerError().body("");
        }
        return CompletableFuture.completedFuture(response);
    }

    /**
     * lista usuarios director
     *
     * @return lista de usuarios
     */
    @Async
    @GetMapping(value = "/lista_director/{id_multiplex}")
    public CompletableFuture<ResponseEntity<?>> listaUsuariosDirector(final @PathVariable("id_multiplex") Integer idMultiplex) {
        ResponseEntity<?> response;
        try {
            response = ResponseEntity.ok(this.usuarioService.listarUsuariosDirector(idMultiplex));
        } catch (Exception e) {
            response = ResponseEntity.internalServerError().body("");
        }
        return CompletableFuture.completedFuture(response);
    }

    /**
     * lista de roles administrador
     *
     * @return lista de roles
     */
    @Async
    @GetMapping(value = "/roles_administrador")
    public CompletableFuture<ResponseEntity<?>> listaRolesAdministrador() {
        ResponseEntity<?> response;
        try {
            response = ResponseEntity.ok(this.usuarioService.listaRolesAdministrador());
        } catch (Exception e) {
            response = ResponseEntity.internalServerError().body("");
        }
        return CompletableFuture.completedFuture(response);
    }

    /**
     * lista de roles director
     *
     * @return lista de roles
     */
    @Async
    @GetMapping(value = "/roles_director")
    public CompletableFuture<ResponseEntity<?>> listaRolesDirector() {
        ResponseEntity<?> response;
        try {
            response = ResponseEntity.ok(this.usuarioService.listaRolesDirector());
        } catch (Exception e) {
            response = ResponseEntity.internalServerError().body("");
        }
        return CompletableFuture.completedFuture(response);
    }

    /**
     * Metodo para guardar un usuario
     *
     * @param usuario usuario a guardar
     * @return usuario guardado
     */
    @Async
    @PostMapping(value = "/registrar")
    public CompletableFuture<ResponseEntity<?>> registrarUsuarioEmpleado(@RequestBody Usuario usuario) {
        ResponseEntity<?> response;
        try {
            response = ResponseEntity.ok(this.usuarioService.registrarUsuarioEmpleado(usuario));
        } catch (Exception e) {
            e.printStackTrace();
            response = ResponseEntity.internalServerError().body("Error al registrar usuario");
        }
        return CompletableFuture.completedFuture(response);
    }

    /**
     * Cambiar el estado de un usuario
     *
     * @param idUsuario id del usuario
     * @return mensaje de exito o error
     */
    @Async
    @PostMapping(value = "/estado")
    public CompletableFuture<ResponseEntity<?>> cambiarEstadoUsuario(@RequestBody final Integer idUsuario) {
        ResponseEntity<?> respuesta;
        try {
            if (idUsuario != null) {
                boolean estado = this.usuarioService.cambiarEstadoUsuario(idUsuario);
                respuesta = estado ? ResponseEntity.ok().build() : ResponseEntity.badRequest().build();
            } else {
                respuesta = ResponseEntity.badRequest().body("Datos incorrectos");
            }
        } catch (Exception e) {
            respuesta = ResponseEntity.internalServerError().body("");
        }
        return CompletableFuture.completedFuture(respuesta);
    }
}


