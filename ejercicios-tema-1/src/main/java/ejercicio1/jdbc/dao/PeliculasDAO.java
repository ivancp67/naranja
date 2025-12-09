package ejercicio1.jdbc.dao;

import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import ejercicio1.jdbc.model.Pelicula;

public class PeliculasDAO {
	public List<Pelicula> findAll(Connection conn) throws SQLException{
		// Select..
		String sql = "select * from film";
		// PreparedStatement
		PreparedStatement stmt = conn.prepareStatement(sql);
		// ResultSet <-- ExecuteQuery
		ResultSet rs = stmt.executeQuery();
		// Crear lista y la lleno recorriendo el resultSet
		while (rs.next()) {
			Pelicula p = new Pelicula();
			p.setId(null);
			
		}
		// return lista
		// (no controlo excepciones, las lanzo sin capturar)
		return null;
	}
}
