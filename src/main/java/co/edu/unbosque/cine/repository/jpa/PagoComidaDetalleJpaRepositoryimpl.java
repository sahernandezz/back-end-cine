package co.edu.unbosque.cine.repository.jpa;

import co.edu.unbosque.cine.model.PagoComidaDetalle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PagoComidaDetalleJpaRepositoryimpl extends JpaRepository<PagoComidaDetalle, Integer> {

    /**
     * Consulta que permite listar los detalles de pago de comida por factura
     *
     * @param idFacturaComida Identificador de la factura de comida
     * @return Lista de detalles de pago de comida
     */
    @Query("SELECT pagoComidaDetalle FROM PagoComidaDetalle pagoComidaDetalle " +
            "WHERE pagoComidaDetalle.facturaComida.id = :id_factura "
    )
    List<PagoComidaDetalle> listaPorFacturaComida(final @Param("id_factura") Integer idFacturaComida);

}
