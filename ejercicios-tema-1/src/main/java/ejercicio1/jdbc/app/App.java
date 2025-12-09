package ejercicio1.jdbc.app;

import java.util.List;

import ejercicio1.jdbc.model.Pelicula;
import ejercicio1.jdbc.services.PeliculasService;

public class App {
	public static void main(String[] args) {
		PeliculasService service = new PeliculasService();
		List<Pelicula> peliculas = service.findShortFilms();
		peliculas.forEach(System.out::println);
	}
}
