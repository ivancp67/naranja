package ejercicio4.jdbc.services;

import java.sql.Connection;
import java.sql.SQLException;

import ejercicio4.jdbc.dao.LineaPedidoDao;
import ejercicio4.jdbc.dao.PedidoDao;
import ejercicio4.jdbc.model.LineaPedido;
import ejercicio4.jdbc.model.Pedido;

public class PedidosServices extends Service{
	
	private LineaPedidoDao daoLinea;
	private PedidoDao daoPedido;
	
	public PedidosServices() {
		daoLinea = new LineaPedidoDao();
		daoPedido = new PedidoDao();
	}
	
	public void registrarPedido(Pedido pedido) throws PedidoException {
		try (Connection conn = abrirConexionSakila()){
			conn.setAutoCommit(false);
			try {
				Long id = daoPedido.insertarPedido(conn, pedido);
				Integer numLinea = 1;
				for (LineaPedido linea : pedido.getLineaPedido()) {
					linea.setIdPedido(id);
					linea.setNumeroLinea(numLinea);
					numLinea++;
					daoLinea.insertarLineaPedido(conn, linea);
				}
				
				conn.commit();
			}catch (SQLException e) {
				conn.rollback();
				throw e;
			}
		} catch (Exception e) {
			throw new PedidoException("Error creando pedidos", e);
		}
	}

}
