package co.edu.unbosque.cine.rest;

import co.edu.unbosque.cine.request.AuthEmpleadoRequest;
import co.edu.unbosque.cine.service.AuthenticationServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Async;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping("/auth/api/v2")
public class AuthenticationRestV2Impl {

    private AuthenticationServiceImpl authenticationService;

    @Autowired
    public AuthenticationRestV2Impl(AuthenticationServiceImpl authenticationService) {
        this.authenticationService = authenticationService;
    }

    @Async
    @PostMapping(value = "/login")
    public CompletableFuture<ResponseEntity<?>> login(@RequestBody final AuthEmpleadoRequest authEmpleadoRequest) {
        ResponseEntity<?> respuesta;
        try {
            if (!authEmpleadoRequest.getCodigo().isEmpty() && !authEmpleadoRequest.getClave().isEmpty()) {
                Map<String, Object> login = authenticationService.loginUsuarioPersonal(authEmpleadoRequest.getCodigo(), authEmpleadoRequest.getClave());
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