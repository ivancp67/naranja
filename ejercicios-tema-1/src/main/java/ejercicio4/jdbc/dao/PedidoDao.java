package ejercicio4.jdbc.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import ejercicio4.jdbc.model.Pedido;

public class PedidoDao {

	public Long insertarPedido(Connection conn, Pedido p) throws SQLException{
		String sql = "insert into pedidos_lineas values(?,?,?,?)";
		PreparedStatement stmt = conn.prepareStatement(sql);
		stmt.setInt(1, p.getIdPedido());
		stmt.setDate(2, Date.valueOf(p.getFechaPedido()));
		stmt.setDate(3, Date.valueOf(p.getFechaEntrega()));
		stmt.setString(4, p.getCliente());
		stmt.execute();
		ResultSet rs = stmt.executeQuery();
		return rs.getLong(1);
		
	}

}
