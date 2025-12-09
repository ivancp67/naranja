package ejercicio4.jdbc.dao;

import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import ejercicio4.jdbc.model.LineaPedido;

public class LineaPedidoDao {

	public void insertarLineaPedido(Connection conn, LineaPedido lp) throws SQLException{
		String sql = "insert into pedidos_lineas values(?,?,?,?)";
		PreparedStatement stmt = conn.prepareStatement(sql);
		stmt.setLong(1, lp.getIdPedido());
		stmt.setInt(2, lp.getNumeroLinea());
		stmt.setString(3, lp.getArticulo());
		stmt.setBigDecimal(4, lp.getPrecio());
		stmt.execute();
	}

}
