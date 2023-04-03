package co.edu.unbosque.cine.rest;

import co.edu.unbosque.cine.model.Usuario;
import co.edu.unbosque.cine.request.UsuarioInfoRequest;
import co.edu.unbosque.cine.request.UsuarioPasswordRequest;
import co.edu.unbosque.cine.service.UsuarioServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Async;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping("/usuario/api/v1")
public class UsuarioRestV1Impl {
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
    public UsuarioRestV1Impl(UsuarioServiceImpl u) {
        this.usuarioService = u;
    }

    /**
     * registrar usuario cliente
     *
     * @param usuario usuario
     * @return usuario registrado
     */
    @Async
    @PostMapping(value = "/registrar")
    public CompletableFuture<ResponseEntity<?>> registrarUsuarioCliente(@RequestBody Usuario usuario) {
        ResponseEntity<?> response;
        try {
            response = ResponseEntity.ok(this.usuarioService.registrarUsuarioCliente(usuario));
        } catch (Exception e) {
            e.printStackTrace();
            response = ResponseEntity.internalServerError().body("Error al registrar usuario");
        }
        return CompletableFuture.completedFuture(response);
    }

    /**
     * actualizar clave usuario
     *
     * @param usuarioPassword usuario y clave
     * @return usuario actualizado
     */
    @Async
    @PostMapping(value = "/cambiar_clave")
    public CompletableFuture<ResponseEntity<?>> cambiarClaveUsuario(@RequestBody UsuarioPasswordRequest usuarioPassword) {
        ResponseEntity<?> response;
        try {
            Optional<Usuario> usuario = this.usuarioService.cambiarClaveCliente(usuarioPassword);
            response = usuario.isPresent() ? ResponseEntity.ok("Clave actualizada")
                    : ResponseEntity.badRequest().body("Usuario o clave incorrecta");
        } catch (Exception e) {
            response = ResponseEntity.internalServerError().body("Error al registrar usuario");
        }
        return CompletableFuture.completedFuture(response);
    }

    /**
     * actualizar información usuario
     *
     * @param usuarioInfo información
     * @return usuario actualizado
     */
    @Async
    @PostMapping(value = "/cambiar_info")
    public CompletableFuture<ResponseEntity<?>> actualizarUsuario(@RequestBody UsuarioInfoRequest usuarioInfo) {
        ResponseEntity<?> response;
        try {
            Optional<Usuario> usuario = this.usuarioService.actualizarInfoUsuarioCliente(usuarioInfo);
            response = usuario.isPresent() ? ResponseEntity.ok(usuario)
                    : ResponseEntity.badRequest().body("Información incorrecta");
        } catch (Exception e) {
            response = ResponseEntity.internalServerError().body("Error al actualizar usuario");
        }
        return CompletableFuture.completedFuture(response);
    }
}
