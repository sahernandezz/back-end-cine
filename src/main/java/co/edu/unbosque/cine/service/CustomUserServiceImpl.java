package co.edu.unbosque.cine.service;

import co.edu.unbosque.cine.model.Usuario;
import co.edu.unbosque.cine.repository.UsuarioRepositoryImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CustomUserServiceImpl implements UserDetailsService {

    private UsuarioRepositoryImpl usuarioRepository;

    @Autowired
    public CustomUserServiceImpl(UsuarioRepositoryImpl usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String correo) throws UsernameNotFoundException {
        Optional<Usuario> user = usuarioRepository.buscarPorCorreo(correo);
        if (user.isEmpty()) {
            throw new UsernameNotFoundException("User Not Found with userName" + correo);
        } else {
            return user.get();
        }
    }
}