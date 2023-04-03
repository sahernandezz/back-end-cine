package co.edu.unbosque.cine.service;

import co.edu.unbosque.cine.model.CategoriaComida;
import co.edu.unbosque.cine.model.Comida;
import co.edu.unbosque.cine.model.Pais;
import co.edu.unbosque.cine.repository.CategoriaComidaRepositoryImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoriaComidaServiceImpl {

    /**
     * Inyección de dependencias
     */
    private CategoriaComidaRepositoryImpl categoriaComidaRepository;

    /**
     * Constructor
     *
     * @param categoriaComidaRepository repositorio
     */
    @Autowired
    public CategoriaComidaServiceImpl(CategoriaComidaRepositoryImpl categoriaComidaRepository) {
        this.categoriaComidaRepository = categoriaComidaRepository;
    }


    /**
     * Método que permite obtener todas las categorías de comida activas por pais
     *
     * @param idPais id del pais
     * @return lista de categorías de comida activas por pais
     */
    public List<CategoriaComida> listaPorPais(final Integer idPais) {
        return this.categoriaComidaRepository.listaPorPaisEstadoEstadoComida(idPais, Pais.ACTIVO, CategoriaComida.ACTIVO, Comida.ACTIVO);
    }
}
