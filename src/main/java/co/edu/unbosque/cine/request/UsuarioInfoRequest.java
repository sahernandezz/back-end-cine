package co.edu.unbosque.cine.request;

import co.edu.unbosque.cine.model.TipoDocumento;
import lombok.Data;

@Data
public class UsuarioInfoRequest {
    private Integer id;
    private String nombre;
    private String apellido;
    private String telefono;
    private String correo;
    private TipoDocumento tipoDocumento;
    private String numeroDocumento;
    private String direccion;
}
