package test.log.ejercicio02.test;

import java.util.List;

import test.log.ejercicio02.client.VideojuegoApiClient;
import test.log.ejercicio02.client.VideojuegoApiClientImpl;
import test.log.ejercicio02.excepcions.NotFoundException;
import test.log.ejercicio02.model.Videojuego;


public class Test {

	public static void main(String[] args) {
		VideojuegoApiClient cliente = new VideojuegoApiClientImpl("4b8c650111714056ae928bd1e9b4a373");
		try {
			// findById
			Videojuego videojuego = cliente.findById("68f66a7a7037b603e8a5ab21");
			System.out.println(videojuego);
			// findById
			videojuego.setAñoPublicacion(videojuego.getAñoPublicacion()+1);
			cliente.update(videojuego);
			System.out.println("Actualizando!!");
			cliente.delete("68f681687037b603e8a5ab71");
			try {
				videojuego = cliente.findById("68f681687037b603e8a5ab71");
				System.out.println("No se ha borrado ");
			} catch (Exception e) {
				System.out.println("Borrado!!");
			}
			videojuego = cliente.findById("68f66a7a7037b603e8a5ab21");
			System.out.println(videojuego);
			
			// findByAñoPublicación
			List<Videojuego> videojuegos = cliente.findByAñoPublicacion(2018);
			videojuegos.forEach(System.out::println);
			
			/* Crear Videojuego
			Videojuego nuevo = new Videojuego();
			nuevo.setAñoPublicacion(2005);
			nuevo.setNombre("Fifa 26");
			nuevo.setPaisOrigen("Kazajistán");
			nuevo.setValoracion(3.0);
			String id = cliente.create(nuevo);
			System.out.println("Creado con id:" + id);
			*/
		}catch (NotFoundException e) {
			System.out.println(e.getMessage());
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
}
