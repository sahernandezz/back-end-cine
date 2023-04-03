package co.edu.unbosque.cine.service;

import co.edu.unbosque.cine.component.HashComponent;
import co.edu.unbosque.cine.model.Rol;
import co.edu.unbosque.cine.model.Usuario;
import co.edu.unbosque.cine.model.UsuarioRol;
import co.edu.unbosque.cine.repository.UsuarioRepositoryImpl;
import co.edu.unbosque.cine.repository.UsuarioRolRepositoryImpl;
import co.edu.unbosque.cine.request.UsuarioInfoRequest;
import co.edu.unbosque.cine.request.UsuarioPasswordRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.*;

@Service
public class UsuarioServiceImpl {

    @Autowired
    private HashComponent hash;

    /**
     * Inyección de dependencias
     */
    private UsuarioRepositoryImpl usuarioRepository;

    /**
     * Inyección de dependencias
     */
    private UsuarioRolRepositoryImpl usuarioRolRepository;

    /**
     * Constructor
     *
     * @param UsuarioRepository    usuarioRepository
     * @param usuarioRolRepository usuarioRolRepository
     */
    @Autowired
    public UsuarioServiceImpl(UsuarioRepositoryImpl UsuarioRepository, UsuarioRolRepositoryImpl usuarioRolRepository) {
        this.usuarioRepository = UsuarioRepository;
        this.usuarioRolRepository = usuarioRolRepository;
    }

    /**
     * Método que permite cambiar el estado de un usuario
     *
     * @param idUsuario id del usuario
     * @return true si se cambia el estado, false si no se cambia
     */
    @Transactional
    public boolean cambiarEstadoUsuario(final Integer idUsuario) {
        boolean respuesta;
        Optional<Usuario> usuario = usuarioRepository.buscarPorId(idUsuario);
        if (usuario.isPresent()) {
            usuario.get().setEstado(usuario.get().getEstado().equals(Usuario.ACTIVO) ? Usuario.INACTIVO : Usuario.ACTIVO);
            usuario = usuarioRepository.guardarUsuario(usuario.get());
            respuesta = usuario.isPresent();
        } else {
            return false;
        }
        return respuesta;
    }

    /**
     * Metodo para asignar la contraseña al usuario
     *
     * @param usuario usuario
     * @return usuario
     */
    private Usuario datosRegistrarUsuarioEmpleado(final Usuario usuario) {
        if (usuario.getId() == null) {
            Optional<Usuario> usuarioOptional;
            usuarioOptional = usuarioRepository.buscarPorNumeroDocumentoAndTipoDocumento(usuario.getNumeroDocumento(), usuario.getTipoDocumento());
            usuarioOptional.ifPresent(value -> usuario.setId(value.getId()));
            //encriptar clave
            usuario.setClave(this.hash.sha1(usuario.getClave()));
        } else {
            if (usuario.getClave() != null) {
                usuario.setClave(this.hash.sha1(usuario.getClave()));
            }
        }

        usuario.setNombre(usuario.getNombre().toLowerCase());
        usuario.setApellido(usuario.getApellido().toLowerCase());

        if (usuario.getEstado() == null) {
            usuario.setEstado(Usuario.ACTIVO);
        }

        return usuario;
    }

    /**
     * gestionar los roles d eun usuario
     *
     * @param roles   roles
     * @param usuario usuario
     * @return Optional<List < UsuarioRol>>
     */
    private Optional<List<UsuarioRol>> guardarRolesUsuario(final List<Rol> roles, final Usuario usuario) {
        Optional<List<UsuarioRol>> usuarioRol;
        this.usuarioRolRepository.eliminarRoles(usuario.getId());
        usuarioRol = this.usuarioRolRepository.guardarUsuarioRol(List.of(
                crearUsuarioRol(usuario, roles.get(0))
        ));

        return usuarioRol;
    }


    /**
     * Método que permite crear un usuario tipo empleado
     *
     * @param usuario usuario
     * @return Usuario
     */
    public Optional<Usuario> registrarUsuarioEmpleado(final Usuario usuario) {
        Usuario usuarioOptional = datosRegistrarUsuarioEmpleado(usuario);
        List<Rol> roles = usuarioOptional.getRoles();
        usuarioOptional.setRoles(null);

        if (roles.contains(getRolCliente())) {
            usuarioOptional.setMultiplex(null);
            usuarioOptional.setCodigoEmpleado(null);
        } else {
            usuarioOptional.setCodigoEmpleado(usuarioOptional.getTipoDocumento().getNombre() + usuarioOptional.getNumeroDocumento());
        }

        Optional<List<UsuarioRol>> usuarioRol = Optional.empty();
        //guardar usuario
        Optional<Usuario> usuarioRegistrado = this.usuarioRepository.guardarUsuario(usuarioOptional);
        if (usuarioRegistrado.isPresent()) {
            usuarioRol = guardarRolesUsuario(roles, usuarioRegistrado.get());
        }
        return usuarioRol.isPresent() ? usuarioRegistrado : Optional.empty();
    }

    /**
     * Registrar usuario cliente
     *
     * @param usuario usuario
     * @return usuario registrado
     */
    @Transactional
    public Optional<Usuario> registrarUsuarioCliente(final Usuario usuario) {
        Optional<UsuarioRol> usuarioRol = Optional.empty();
        //encriptar clave
        usuario.setClave(this.hash.sha1(usuario.getClave()));
        usuario.setNombre(usuario.getNombre().toLowerCase());
        usuario.setApellido(usuario.getApellido().toLowerCase());
        //guardar usuario
        Optional<Usuario> usuarioRegistrado = this.usuarioRepository.guardarUsuario(usuario);
        if (usuarioRegistrado.isPresent()) {
            usuarioRol = this.usuarioRolRepository.guardarUsuarioRol(
                    this.crearUsuarioRol(usuarioRegistrado.get(), getRolCliente()));
        }
        return usuarioRol.isPresent() ? usuarioRegistrado : Optional.empty();
    }

    /**
     * Cambiar la clave de un usuario
     *
     * @param usuarioPassword usuarioPassword
     * @return usuario
     */
    @Transactional
    public Optional<Usuario> cambiarClaveCliente(final UsuarioPasswordRequest usuarioPassword) {
        Optional<Usuario> usuarioRegistrado = this.usuarioRepository.buscarPorId(usuarioPassword.getId());
            usuarioRegistrado.get().setClave(this.hash.sha1(usuarioPassword.getClave()));
            usuarioRegistrado = this.usuarioRepository.guardarUsuario(usuarioRegistrado.get());
        return usuarioRegistrado;
    }

    /**
     * Actualizar información de un usuario
     *
     * @param usuarioInfo usuarioInfo
     * @return usuario
     */
    @Transactional
    public Optional<Usuario> actualizarInfoUsuarioCliente(final UsuarioInfoRequest usuarioInfo) {
        Optional<Usuario> usuarioRegistrado = this.usuarioRepository.buscarPorId(usuarioInfo.getId());
            usuarioRegistrado.get().setNombre(usuarioInfo.getNombre().toLowerCase());
            usuarioRegistrado.get().setApellido(usuarioInfo.getApellido().toLowerCase());
            usuarioRegistrado.get().setTelefono(usuarioInfo.getTelefono());
            usuarioRegistrado.get().setCorreo(usuarioInfo.getCorreo());
            usuarioRegistrado.get().setTipoDocumento(usuarioInfo.getTipoDocumento());
            usuarioRegistrado.get().setNumeroDocumento(usuarioInfo.getNumeroDocumento());
            usuarioRegistrado.get().setDireccion(usuarioInfo.getDireccion());
            usuarioRegistrado = this.usuarioRepository.guardarUsuario(usuarioRegistrado.get());

        return usuarioRegistrado;
    }

    /**
     * listar roles que puede asignar un usuario administrador
     *
     * @return lista de roles
     */
    public List<Rol> listaRolesAdministrador() {
        return List.of(
                getRolAdministrador(),
                getRolDirector(),
                getRolCajeroPelicula(),
                getRolCajeroComida(),
                getRolCliente()
        );
    }

    /**
     * listar roles que puede asignar un usuario director
     *
     * @return lista de roles
     */
    public List<Rol> listaRolesDirector() {
        return List.of(
                getRolCajeroPelicula(),
                getRolCajeroComida()
        );
    }

    /**
     * Lista de usuarios
     *
     * @return lista de usuarios
     */
    public List<Usuario> listarUsuarios() {
        return this.usuarioRepository.listarUsuarios();
    }

    /**
     * lista de usuarios de un director
     *
     * @param idMultiplex idMultiplex del director
     * @return lista de usuarios
     */
    public List<Usuario> listarUsuariosDirector(final Integer idMultiplex) {
        return this.usuarioRepository.listarUsuariosDirector(idMultiplex, getRolCajeroComida().getId(), getRolCajeroPelicula().getId());
    }

    /**
     * Método que permite crear un usuario rol
     *
     * @param usuario usuario
     * @param rol     rol
     * @return UsuarioRol
     */
    private UsuarioRol crearUsuarioRol(Usuario usuario, Rol rol) {
        UsuarioRol usuarioRol = new UsuarioRol();
        usuarioRol.setUsuario(usuario);
        usuarioRol.setRol(rol);
        return usuarioRol;
    }

    /**
     * Método que permite obtener el rol de administrador
     *
     * @return Rol administrador
     */
    private Rol getRolAdministrador() {
        Rol rol = new Rol();
        rol.setId(1);
        rol.setNombre("Administrador");
        return rol;
    }

    /**
     * Método que retorna el rol de cliente
     *
     * @return Rol cliente
     */
    private Rol getRolCliente() {
        Rol rol = new Rol();
        rol.setId(2);
        rol.setNombre("Cliente");
        return rol;
    }

    /**
     * Método que retorna el rol de director
     *
     * @return Rol director
     */
    private Rol getRolDirector() {
        Rol rol = new Rol();
        rol.setId(3);
        rol.setNombre("Director");
        return rol;
    }

    /**
     * Método que retorna el rol de cajero pelicula
     *
     * @return Rol cajero pelicula
     */
    private Rol getRolCajeroPelicula() {
        Rol rol = new Rol();
        rol.setId(4);
        rol.setNombre("Cajero pelicula");
        return rol;
    }

    /**
     * Método que retorna el rol de cajero comida
     *
     * @return Rol cajero comida
     */
    private Rol getRolCajeroComida() {
        Rol rol = new Rol();
        rol.setId(5);
        rol.setNombre("Cajero comida");
        return rol;
    }
}
