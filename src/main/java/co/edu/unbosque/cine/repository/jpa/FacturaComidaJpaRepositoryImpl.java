package co.edu.unbosque.cine.repository.jpa;

import co.edu.unbosque.cine.model.FacturaComida;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface FacturaComidaJpaRepositoryImpl extends JpaRepository<FacturaComida, Integer> {

    /**
     * Consulta que permite listar las facturas de comida por usuario
     *
     * @param idUsuario     Identificador del usuario
     * @param estadoFactura Estado de la factura
     * @return Lista de facturas de comida
     */
    @Query("SELECT factura FROM FacturaComida factura " +
            "WHERE factura.usuario.id = :id_usuario " +
            "AND factura.estado = :estado_factura "
    )
    List<FacturaComida> facturaPorCliente(final @Param("id_usuario") Integer idUsuario, final @Param("estado_factura") String estadoFactura);
}
