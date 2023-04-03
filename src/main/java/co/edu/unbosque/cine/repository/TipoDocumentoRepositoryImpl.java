package co.edu.unbosque.cine.repository;

import co.edu.unbosque.cine.model.TipoDocumento;
import co.edu.unbosque.cine.repository.jpa.TipoDocumentoPaisJpaRepositoryImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class TipoDocumentoRepositoryImpl {

    /**
     * jpa repository de tipo documento
     */
    private TipoDocumentoPaisJpaRepositoryImpl tipoDocumentoJpa;

    /**
     * constructor
     *
     * @param t tipo documento jpa
     */
    @Autowired
    public TipoDocumentoRepositoryImpl(TipoDocumentoPaisJpaRepositoryImpl t) {
        this.tipoDocumentoJpa = t;
    }

    /**
     * Metodo que permite consultar los tipos de documentos por estado
     *
     * @param idPais              id del pais
     * @param estadoTipoDocumento estado del tipo de documento
     * @param estadoPais          estado del pais
     * @return lista de tipos de documentos
     */
    public List<TipoDocumento> listarPorEstadoPorPaisPorEstado(final Integer idPais, final String estadoTipoDocumento, final String estadoPais) {
        return tipoDocumentoJpa.listaPorPaisPorEstado(idPais, estadoTipoDocumento, estadoPais);
    }

}
