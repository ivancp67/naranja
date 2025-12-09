package ejercicio4.jdbc.model;

import java.time.LocalDate;
import java.util.List;

import lombok.Data;

@Data
public class Pedido {
	
	private Integer idPedido;
	private LocalDate fechaPedido;
	private LocalDate fechaEntrega;
	private String cliente;
	private List<LineaPedido> lineaPedido;

}
