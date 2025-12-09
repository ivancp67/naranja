package ejercicio3.jdbc.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import ejercicio3.jdbc.model.Pago;

public class PagoDao {

	public List<Pago> listarCliente(Connection conn, Integer id) throws SQLException{
		List<Pago> lista = new ArrayList<Pago>();
		String sql = "select * from payment where customer_id = ?";
		PreparedStatement stmt = conn.prepareStatement(sql);
		stmt.setInt(1, id);
		ResultSet rs = stmt.executeQuery();
		while(rs.next()) {
			Pago p = new Pago();
			Date date = rs.getDate("payment_date");
			LocalDate fecha = date.toLocalDate();
			p.setImporte(rs.getInt("amount"));
			p.setFecha(fecha);
			lista.add(p);
		}
		return lista;
	}

}
