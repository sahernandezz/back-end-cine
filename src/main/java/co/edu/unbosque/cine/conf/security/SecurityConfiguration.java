package co.edu.unbosque.cine.conf.security;

import co.edu.unbosque.cine.component.JWTTokenHelperComponent;
import co.edu.unbosque.cine.service.CustomUserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.DelegatingPasswordEncoder;
import org.springframework.security.crypto.password.MessageDigestPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import java.util.HashMap;

import static org.springframework.security.crypto.password.NoOpPasswordEncoder.*;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    private CustomUserServiceImpl userService;

    private JWTTokenHelperComponent jWTTokenHelper;

    private AuthenticationEntryPoint entryPoint;

    @Autowired
    public SecurityConfiguration(CustomUserServiceImpl userService, JWTTokenHelperComponent jWTTokenHelper, AuthenticationEntryPoint entryPoint) {
        this.userService = userService;
        this.jWTTokenHelper = jWTTokenHelper;
        this.entryPoint = entryPoint;
    }

    @Override
    protected void configure(final AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(this.userService).passwordEncoder(this.passwordEncoder());
    }

    @Bean
    protected PasswordEncoder passwordEncoder() {
        final HashMap<String, PasswordEncoder> encoders = new HashMap<>();
        encoders.put("noop", getInstance());
        encoders.put("bcrypt", new BCryptPasswordEncoder());
        encoders.put(null, new MessageDigestPasswordEncoder("SHA-1"));
        encoders.put("SHA-1", new MessageDigestPasswordEncoder("SHA-1"));
        return new DelegatingPasswordEncoder("bcrypt", encoders);
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Override
    protected void configure(final HttpSecurity http) throws Exception {
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and().exceptionHandling()
                .authenticationEntryPoint(this.entryPoint).and()
                .authorizeRequests(
                        (request) -> request
                                .antMatchers(
                                        "/",
                                        "/*.css",
                                        "/*.js",
                                        "/*.ico",
                                        "/*.svg",
                                        "/assets/**",
                                        "/usuario/api/v1/registrar",
                                        "/auth/api/v1/*",
                                        "/auth/api/v2/*",
                                        "/categoria_comida/api/v1/lista_pais/*",
                                        "/ciudad/api/v1/lista_pais/*",
                                        "/comida/api/v1/lista_pais/*",
                                        "/pais/api/v1/*",
                                        "/pelicula/api/v1/cartelera/*",
                                        "/pelicula/api/v1/proximamente/*",
                                        "/pelicula/api/v1/titulo/*/*",
                                        "/pelicula/api/v1/puntaje/*",
                                        "/funcion/api/v1/funciones/*/*",
                                        "/tipo_documento/api/v1/lista/*"
                                ).permitAll()

                                .antMatchers(
                                        "/cliente/api/v1/puntos/*",
                                        "/cliente/api/v1/puntos/*",
                                        "/cliente/api/v1/facturas/comida/*",
                                        "/cliente/api/v1/facturas/funcion/*",
                                        "/pelicula/api/v1/puntaje/*/*",
                                        "/pelicula/api/v1/puntaje",
                                        "/medio_pago/api/v1/lista/*",
                                        "/funcion/api/v1/sillas/*",
                                        "/funcion/api/v1/pago"
                                ).authenticated()

                                .antMatchers("/usuario/api/v1/cambiar_clave",
                                        "/usuario/api/v1/cambiar_info"
                                ).hasAuthority("Cliente")

                                .antMatchers(
                                        "/usuario/api/v2/roles_director",
                                        "/usuario/api/v2/lista_director/*"
                                ).hasAuthority("Director")

                                .antMatchers(
                                        "/reporte/api/v1/pelicula/lista/puntaje",
                                        "/reporte/api/v1/pelicula/cantidad",
                                        "/usuario/api/v2/estado",
                                        "/usuario/api/v2/registrar"
                                ).hasAnyAuthority("Director", "Administrador")

                                .antMatchers(
                                        "/**"
                                ).hasAuthority("Administrador")
                                .anyRequest().fullyAuthenticated()
                ).addFilterBefore(new JWTAuthenticationFilter(this.userService, this.jWTTokenHelper),
                        UsernamePasswordAuthenticationFilter.class);
        http.csrf().disable().cors().and().headers().frameOptions().disable();
    }
}
