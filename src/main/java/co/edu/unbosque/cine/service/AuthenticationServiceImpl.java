package co.edu.unbosque.cine.service;

import co.edu.unbosque.cine.component.HashComponent;
import co.edu.unbosque.cine.component.JWTTokenHelperComponent;
import co.edu.unbosque.cine.model.Usuario;
import co.edu.unbosque.cine.repository.UsuarioRepositoryImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Service
public class AuthenticationServiceImpl {

    private UsuarioRepositoryImpl usuarioRepository;

    @Autowired
    private HashComponent hash;

    private AuthenticationManager authentication;

    private JWTTokenHelperComponent jWTTokenHelper;

    @Autowired
    public AuthenticationServiceImpl(UsuarioRepositoryImpl usuarioRepository, AuthenticationManager authentication, JWTTokenHelperComponent jWTTokenHelper) {
        this.usuarioRepository = usuarioRepository;
        this.authentication = authentication;
        this.jWTTokenHelper = jWTTokenHelper;
    }

    private final static String INCORRECTO = "correo o clave incorrectos";

    /**
     * Metodo que permite autenticar un usuario cliente
     *
     * @param correo correo del usuario
     * @param clave  clave del usuario
     * @return token de autenticacion
     */
    public Map<String, Object> loginUsuarioCliente(final String correo, final String clave) {
        Map<String, Object> respuesta = new HashMap<>();
        Optional<Usuario> usuario = this.usuarioRepository.buscarPorCorreo(correo);
        if (usuario.isPresent()) {
            if (usuario.get().getClave().equals(hash.sha1(clave))) {
                final Authentication auth = this.authentication.authenticate(new UsernamePasswordAuthenticationToken(correo, clave));
                SecurityContextHolder.getContext().setAuthentication(auth);
                usuario.get().setClave(null);
                respuesta.put("usuario", usuario.get());
                respuesta.put("token", this.jWTTokenHelper.generateToken(correo));
            } else {
                respuesta = Map.of("message", INCORRECTO);
            }
        } else {
            respuesta = Map.of("message", INCORRECTO);
        }
        return respuesta;
    }

    /**
     * Metodo que permite autenticar un usuario empleado
     *
     * @param codigoEmpleado codigo del empleado
     * @param clave          clave del usuario
     * @return token de autenticacion
     */
    public Map<String, Object> loginUsuarioPersonal(final String codigoEmpleado, final String clave) {
        Map<String, Object> respuesta = new HashMap<>();
        Optional<Usuario> usuario = this.usuarioRepository.buscarPorCodigoEmpleado(codigoEmpleado);
        if (usuario.isPresent()) {
            if (usuario.get().getClave().equals(hash.sha1(clave))) {
                final Authentication auth = this.authentication.authenticate(new UsernamePasswordAuthenticationToken(usuario.get().getCorreo(), clave));
                SecurityContextHolder.getContext().setAuthentication(auth);
                usuario.get().setClave(null);
                respuesta.put("usuario", usuario.get());
                respuesta.put("token", this.jWTTokenHelper.generateToken(usuario.get().getCorreo()));
            } else {
                respuesta = Map.of("message", INCORRECTO);
            }
        } else {
            respuesta = Map.of("message", INCORRECTO);
        }
        return respuesta;
    }
}