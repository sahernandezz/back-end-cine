package co.edu.unbosque.cine.request;

import lombok.Data;

@Data
public class AuthClienteRequest {
    private String clave;
    private String correo;
}
