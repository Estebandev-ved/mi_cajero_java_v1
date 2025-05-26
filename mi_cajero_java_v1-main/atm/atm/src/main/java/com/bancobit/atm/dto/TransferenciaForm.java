package dto;

import lombok.Data;

@Data
public class TransferenciaForm {
    private String cuentaOrigen;
    private String cuentaDestino;
    private double monto;
}
