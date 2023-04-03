package co.edu.unbosque.cine.repository;

import co.edu.unbosque.cine.model.Funcion;
import co.edu.unbosque.cine.model.FuncionSalaSilla;
import co.edu.unbosque.cine.repository.jpa.FuncionesJpaRepositoryImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class FuncionRepositoryImpl {

    /**
     * JPA repository for Funcion
     */
    private FuncionesJpaRepositoryImpl funcionesJpaRepository;

    /**
     * Constructor
     *
     * @param funcionesJpaRepository JPA repository for Funcion
     */
    @Autowired
    public FuncionRepositoryImpl(FuncionesJpaRepositoryImpl funcionesJpaRepository) {
        this.funcionesJpaRepository = funcionesJpaRepository;
    }

    /**
     * funciones por ciudad y pelicula
     *
     * @param idCiudad       id de la ciudad
     * @param estadoCiudad   estado de la ciudad
     * @param idPelicula     id de la pelicula
     * @param estadoPelicula estado de la pelicula
     * @param estadoFuncion  estado de la funcion
     * @return lista de funciones
     */
    public List<Funcion> funcionesPorCiudadPelicula(final Integer idCiudad, final String estadoCiudad, final Integer idPelicula, final String estadoPelicula, final String estadoFuncion) {
        return this.funcionesJpaRepository.funcionesPorCiudadPelicula(idCiudad, estadoCiudad, idPelicula, estadoPelicula, estadoFuncion);
    }

    /**
     * sillas por funcion y estado
     *
     * @param idFuncion   id de la funcion
     * @param estadoSilla estado de la silla
     * @return lista de sillas
     */
    public List<FuncionSalaSilla> sillasPorFuncionPorEstado(final Integer idFuncion, final String estadoSilla) {
        return this.funcionesJpaRepository.sillasPorFuncionPorEstado(idFuncion, estadoSilla);
    }

    /**
     * funcion por id
     *
     * @param idFuncion id de la funcion
     * @return funcion
     */
    public Optional<Funcion> buscarPorId(final Integer idFuncion) {
        return this.funcionesJpaRepository.findById(idFuncion);
    }
}
