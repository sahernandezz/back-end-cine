package co.edu.unbosque.cine.repository.jpa;

import co.edu.unbosque.cine.model.TipoDocumento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface TipoDocumentoPaisJpaRepositoryImpl extends JpaRepository<TipoDocumento, Integer> {

    /**
     * Metodo que permite consultar los tipos de documentos por estado
     *
     * @param idPais              id del pais
     * @param estadoTipoDocumento estado del tipo de documento
     * @param estadoPais          estado del pais
     * @return lista de tipos de documentos
     */
    @Query("SELECT t FROM TipoDocumento t WHERE t.pais.id = :id_pais " +
            "and t.pais.estado = :estado_pais " +
            "and t.estado = :estado_tipo_documento "
    )
    List<TipoDocumento> listaPorPaisPorEstado(final @Param("id_pais") Integer idPais,
                                              final @Param("estado_tipo_documento") String estadoTipoDocumento,
                                              final @Param("estado_pais") String estadoPais);
}
