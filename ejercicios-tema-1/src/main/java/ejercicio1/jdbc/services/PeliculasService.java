package ejercicio1.jdbc.services;



import java.net.ConnectException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import ejercicio1.jdbc.dao.PeliculasDAO;
import ejercicio1.jdbc.model.Pelicula;

public class PeliculasService {
private PeliculasDAO dao;
	
	public PeliculasService() {
		dao = new PeliculasDAO();
	}

	public List<Pelicula> findShortFilms(){
		// 1. Abrir conexión
		// 2. Invocar el método findAll del dao --> dao.findAll(conn)
		// 3. Con la lista que me devuelve el anterior --> Filtrar películas
		// 4. Devolver la lista filtrada
		// 5. Capturar excepciones y lanzar las que se consideren
		
		return null;
	}

	
}
