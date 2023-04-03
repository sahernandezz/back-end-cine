package co.edu.unbosque.cine.request;

import lombok.Data;

@Data
public class UsuarioPasswordRequest {
    private Integer id;
    private String clave;
}
