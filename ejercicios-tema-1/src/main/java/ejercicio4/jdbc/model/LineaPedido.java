package ejercicio4.jdbc.model;

import java.math.BigDecimal;

import lombok.Data;

@Data
public class LineaPedido {
	
	private Long idPedido;
	private Integer numeroLinea;
	private String articulo;
	private BigDecimal precio;

}
