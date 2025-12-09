package ejercicio4.jdbc.app;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;

import ejercicio4.jdbc.model.LineaPedido;
import ejercicio4.jdbc.model.Pedido;
import ejercicio4.jdbc.services.PedidosServices;

public class App {
	public static void main(String[] args) {
		PedidosServices services = new PedidosServices();
		
		Pedido pedido = new Pedido();
		pedido.setFechaPedido(LocalDate.now());
		pedido.setFechaEntrega(LocalDate.of(2024, 12, 1));
		pedido.setCliente("Lucas Cangrejo");
		pedido.setLineaPedido(new ArrayList<LineaPedido>());
		
		for (int j = 1; j <= 3; j++) {
			LineaPedido linea = new LineaPedido();
			linea.setArticulo("Artículo " + j);
			linea.setPrecio(new BigDecimal(938));
			pedido.getLineaPedido().add(linea);
		}
		
		try {
			
		} catch (Exception e) {
			
		}
		
	}

}
