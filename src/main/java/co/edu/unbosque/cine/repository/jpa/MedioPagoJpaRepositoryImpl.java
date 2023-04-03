package co.edu.unbosque.cine.repository.jpa;

import co.edu.unbosque.cine.model.MedioPago;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface MedioPagoJpaRepositoryImpl extends JpaRepository<MedioPago, Integer> {

    /**
     * Método que permite consultar los medios de pago por país y estado
     *
     * @param idPais identificador del país
     * @param estado estado del medio de pago
     * @return lista de medios de pago
     */
    @Query("SELECT medio_pago FROM MedioPago medio_pago " +
            "WHERE medio_pago.pais.id = :id_pais " +
            "AND medio_pago.estado = :estado"
    )
    List<MedioPago> listaPorPaisPorEstado(final @Param("id_pais") Integer idPais, final @Param("estado") String estado);
}
