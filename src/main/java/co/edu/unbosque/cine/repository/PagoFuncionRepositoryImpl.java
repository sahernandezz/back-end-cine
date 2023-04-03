package co.edu.unbosque.cine.repository;

import co.edu.unbosque.cine.model.PagoFuncion;
import co.edu.unbosque.cine.repository.jpa.PagoFuncionJpaRepositoryImpl;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.Optional;

@Repository
public class PagoFuncionRepositoryImpl {

    /**
     * Inyección de dependencias
     */
    private PagoFuncionJpaRepositoryImpl pagoJpaRepository;

    /**
     * Constructor
     *
     * @param pagoJpaRepository Inyección de dependencias
     */
    public PagoFuncionRepositoryImpl(PagoFuncionJpaRepositoryImpl pagoJpaRepository) {
        this.pagoJpaRepository = pagoJpaRepository;
    }

    /**
     * Guardar un pago
     *
     * @param pagoFuncion pago a guardar
     * @return pago guardado
     */
    @Transactional
    public Optional<PagoFuncion> guardarPago(final PagoFuncion pagoFuncion) {
        return Optional.of(this.pagoJpaRepository.save(pagoFuncion));
    }
}
