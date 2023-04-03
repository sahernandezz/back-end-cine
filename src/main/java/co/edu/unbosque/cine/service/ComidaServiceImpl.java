package co.edu.unbosque.cine.service;

import co.edu.unbosque.cine.model.Comida;
import co.edu.unbosque.cine.repository.ComidaRepositoryImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ComidaServiceImpl {

    /**
     * Inyección de dependencias
     */
    private ComidaRepositoryImpl comidaRepository;

    @Autowired
    public ComidaServiceImpl(ComidaRepositoryImpl comidaRepository) {
        this.comidaRepository = comidaRepository;
    }


    /**
     * Método que permite listar las comidas por pais
     *
     * @param idPais identificador del pais
     * @return lista de comidas por pais
     */
    public List<Comida> listaDeComidasPorPais(final Integer idPais) {
        return comidaRepository.listaDeComidasActivasPorPais(idPais);
    }

}
