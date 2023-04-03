package co.edu.unbosque.cine.repository;

import co.edu.unbosque.cine.model.Articulo;
import co.edu.unbosque.cine.repository.jpa.ArticuloJpaRepositoryImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class ArticuloRepositoryImpl {

    /**
     * Inyección de dependencias
     */
    private ArticuloJpaRepositoryImpl articuloJpaRepository;

    /**
     * Constructor
     *
     * @param articuloJpaRepository inyección de dependencias
     */
    @Autowired
    public ArticuloRepositoryImpl(ArticuloJpaRepositoryImpl articuloJpaRepository) {
        this.articuloJpaRepository = articuloJpaRepository;
    }

    /**
     * Buscar articulo por codigo, pais y estado
     *
     * @param codigo codigo del articulo
     * @param idPais id del pais
     * @param estado estado del articulo
     * @return articulo
     */
    public Optional<Articulo> buscarPorCodigoPorPaisPorEstado(final Integer codigo, final Integer idPais, final String estado) {
        return Optional.of(this.articuloJpaRepository.buscarPorCodigoPorPaisPorEstado(codigo, idPais, estado));
    }
}
