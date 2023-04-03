package co.edu.unbosque.cine.rest;

import co.edu.unbosque.cine.request.AuthClienteRequest;
import co.edu.unbosque.cine.service.AuthenticationServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Async;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping("/auth/api/v1")
public class AuthenticationRestV1Impl {

    private AuthenticationServiceImpl authenticationService;

    @Autowired
    public AuthenticationRestV1Impl(AuthenticationServiceImpl authenticationService) {
        this.authenticationService = authenticationService;
    }

    @Async
    @PostMapping(value = "/login")
    public CompletableFuture<ResponseEntity<?>> login(@RequestBody final AuthClienteRequest authClienteRequest) {
        ResponseEntity<?> respuesta;
        try {
            if (!authClienteRequest.getCorreo().isEmpty() && !authClienteRequest.getClave().isEmpty()) {
                Map<String, Object> login = authenticationService.loginUsuarioCliente(authClienteRequest.getCorreo(), authClienteRequest.getClave());
                respuesta = login.get("message") == null ? ResponseEntity.ok(login)
                        : ResponseEntity.badRequest().body(login);
            } else {
                respuesta = ResponseEntity.badRequest().body("Datos incorrectos");
            }
        } catch (Exception e) {
            e.printStackTrace();
            respuesta = ResponseEntity.internalServerError().body("");
        }
        return CompletableFuture.completedFuture(respuesta);
    }
}