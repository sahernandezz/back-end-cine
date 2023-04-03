package co.edu.unbosque.cine.service;

import co.edu.unbosque.cine.model.Pais;
import co.edu.unbosque.cine.model.TipoDocumento;
import co.edu.unbosque.cine.repository.TipoDocumentoRepositoryImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TipoDocumentoServiceImpl {
    /**
     * Inyección de dependencias
     */
    private TipoDocumentoRepositoryImpl tipoDocumentoRepository;

    /**
     * Constructor
     *
     * @param t tipoDocumentoRepository
     */
    @Autowired
    public TipoDocumentoServiceImpl(TipoDocumentoRepositoryImpl t) {
        this.tipoDocumentoRepository = t;
    }

    /**
     * Metodo que permite consultar los tipos de documentos por pais
     *
     * @param idPais id del pais
     * @return lista de tipos de documentos
     */
    public List<TipoDocumento> listarPorPais(final Integer idPais) {
        return tipoDocumentoRepository.listarPorEstadoPorPaisPorEstado(idPais, TipoDocumento.ACTIVO, Pais.ACTIVO);
    }


}
