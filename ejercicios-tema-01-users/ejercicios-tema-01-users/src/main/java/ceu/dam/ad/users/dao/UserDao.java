package ceu.dam.ad.users.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

import ceu.dam.ad.users.model.User;

public class UserDao {
	
	private User consultarUser(PreparedStatement stmt) throws SQLException {
		ResultSet rs = stmt.executeQuery();
		if (rs.next()) {
			User user = new User();
			Date date1 = rs.getDate("fecha_alta");
			LocalDate fecha1 = date1.toLocalDate();
			Date date2 = rs.getDate("fecha_ult_login");
			LocalDate fecha2 = date2.toLocalDate();
			user.setId(rs.getLong("id"));
			user.setEmail(rs.getString("email"));
			user.setPassword(rs.getString("password"));
			user.setCreatedDate(fecha1);
			user.setLastLoginDate(fecha2);
			return user;
		}
		return null;
	}

	/** Debe insertar un usuario en BBDD. Devuelve el ID generado. */
	public Long insert(Connection conn, User user) throws SQLException{
		String sql = "insert into usuarios values(?,?,?,?,?,?)";
		PreparedStatement stmt = conn.prepareStatement(sql);
		stmt.setLong(1, user.getId());
		stmt.setString(2, user.getUsername());
		stmt.setString(3, user.getEmail());
		stmt.setString(4, user.getPassword());
		stmt.setDate(5, Date.valueOf(user.getCreatedDate()));
		stmt.setDate(6, Date.valueOf(user.getLastLoginDate()));
		return user.getId();
		
	}
	
	/** Debe consultar un usuario por su email y devolverlo. Si no existe, devolverá null */
	public User getByEmail(Connection conn, String email) throws SQLException {
		String sql = "select * from usuarios where email = ?";
		PreparedStatement stmt = conn.prepareStatement(sql);
		stmt.setString(1, email);
		return consultarUser(stmt);
	}

	
	/** Debe consultar un usuario por su ID y devolverlo. Si no existe, devolverá null.  NOTA: no dupliques código */
	public User getById(Connection conn, Long id) throws SQLException{
		String sql = "select * from usuarios where id = ?";
		PreparedStatement stmt = conn.prepareStatement(sql);
		stmt.setLong(1, id);
		return consultarUser(stmt);
	}


	/** Debe consultar un usuario por su email y devolverlo. Si no existe, devolverá null. NOTA: no dupliques código  */
	public User getByUserName(Connection conn, String userName) throws SQLException{
		String sql = "select * from usuarios where username = ?";
		PreparedStatement stmt = conn.prepareStatement(sql);
		stmt.setString(1, userName);
		return consultarUser(stmt);
	}

	/** Debe actualizar todos los datos de un usuario y devolver el número de registros actualizados. */
	public Integer update(Connection conn, User user) throws SQLException{
		String sql = "update usuarios set id = ?, email = ?, password = ? where id = ?";
		PreparedStatement stmt = conn.prepareStatement(sql);
		stmt.setLong(1, user.getId());
		stmt.setString(2, user.getEmail());
		stmt.setString(3, user.getPassword());
		stmt.setLong(4, user.getId());
		return null;
	}
	
}
