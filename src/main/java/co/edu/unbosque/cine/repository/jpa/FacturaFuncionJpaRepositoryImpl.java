package co.edu.unbosque.cine.repository.jpa;

import co.edu.unbosque.cine.model.FacturaFuncion;
import co.edu.unbosque.cine.model.Multiplex;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface FacturaFuncionJpaRepositoryImpl extends JpaRepository<FacturaFuncion, Integer> {

    /**
     * Consulta que permite listar las facturas de funcion por usuario
     *
     * @param idUsuario     Identificador del usuario
     * @param estadoFactura Estado de la factura
     * @return Lista de facturas de funcion
     */
    @Query("SELECT factura FROM FacturaFuncion factura " +
            "WHERE factura.usuario.id = :id_usuario " +
            "AND factura.estado = :estado_factura "
    )
    List<FacturaFuncion> facturaPorCliente(final @Param("id_usuario") Integer idUsuario, final @Param("estado_factura") String estadoFactura);
}
