package co.edu.unbosque.cine.repository;

import co.edu.unbosque.cine.model.FuncionSalaSilla;
import co.edu.unbosque.cine.repository.jpa.FuncionSalaSillaJpaRepositoryImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public class FuncionSalaSillaRepositoryImpl {

    /**
     * Inyección de dependencias
     */
    private FuncionSalaSillaJpaRepositoryImpl funcionSalaSillaJpaRepository;


    /**
     * Constructor
     *
     * @param funcionSalaSillaJpaRepository inyección de dependencias
     */
    @Autowired
    public FuncionSalaSillaRepositoryImpl(FuncionSalaSillaJpaRepositoryImpl funcionSalaSillaJpaRepository) {
        this.funcionSalaSillaJpaRepository = funcionSalaSillaJpaRepository;
    }

    /**
     * Método que permite guardar una lista de FuncionSalaSilla
     *
     * @param funcionSalaSillas lista de FuncionSalaSilla
     * @return lista de FuncionSalaSilla
     */
    @Transactional
    public List<FuncionSalaSilla> guardarTodasLasSillas(List<FuncionSalaSilla> funcionSalaSillas) {
        return funcionSalaSillaJpaRepository.saveAll(funcionSalaSillas);
    }
}
