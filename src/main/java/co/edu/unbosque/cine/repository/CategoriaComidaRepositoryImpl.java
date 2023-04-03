package co.edu.unbosque.cine.repository;

import co.edu.unbosque.cine.model.CategoriaComida;
import co.edu.unbosque.cine.model.Comida;
import co.edu.unbosque.cine.repository.jpa.CategoriaComidaJpaRepositoryImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CategoriaComidaRepositoryImpl {

    /**
     * Inyección de dependencias jpa
     */
    private CategoriaComidaJpaRepositoryImpl categoriaComidaJpa;

    /**
     * Constructor
     *
     * @param categoriaComidaJpa repositorio jpa
     */
    @Autowired
    public CategoriaComidaRepositoryImpl(CategoriaComidaJpaRepositoryImpl categoriaComidaJpa) {
        this.categoriaComidaJpa = categoriaComidaJpa;
    }


    /**
     * Método que lista las categorias de comida por pais y estado
     *
     * @param idPais         id del pais
     * @param estadoPais      estado del pais
     * @param estadoCategoria estado de la categoria de comida
     * @param estadoComida    estado de la comida
     * @return lista de categorias de comida
     */
    public List<CategoriaComida> listaPorPaisEstadoEstadoComida(final Integer idPais, final String estadoPais, final String estadoCategoria, final String estadoComida) {
        return this.categoriaComidaJpa.listaPorPaisEstadoEstadoComida(idPais, estadoPais, estadoCategoria, estadoComida);
    }
}
