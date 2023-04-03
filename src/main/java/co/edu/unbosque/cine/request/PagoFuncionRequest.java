package co.edu.unbosque.cine.request;

import co.edu.unbosque.cine.model.MedioPago;
import lombok.Data;

import java.util.List;

@Data
public class PagoFuncionRequest {
    private Integer idFuncion;
    private List<SillaRequest> sillas;
    private Integer cantidadSillas;
    private Integer idUsuario;
    private String tipoLocalidad;
    private MedioPago medioPago;
}
