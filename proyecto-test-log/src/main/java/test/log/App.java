package test.log;

import java.net.URI;

import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpRequest.BodyPublisher;
import java.net.http.HttpRequest.BodyPublishers;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.util.Arrays;
import java.util.List;

import com.google.gson.Gson;

public class App {

		public static void main(String[] args) {
			
			try {
				Pelicula nueva = new Pelicula();
				nueva.setDirector("Iván Córdoba");
				nueva.setDuracion(100);
				nueva.setOscars(3);
				nueva.setTitulo("Quiero dejar esto");
				
				String json = new Gson().toJson(nueva);
				System.out.println("Request body:" + json);
				
				URI url = new URI("https://crudcrud.com/api/03c86acff3f944b78424351ebcf5c3ab/pelicula");
				HttpClient client = HttpClient.newHttpClient();
				HttpRequest request = HttpRequest.newBuilder(url).setHeader("content-type", "application/json")
						.POST(BodyPublishers.ofString(json)).build();
				
				HttpResponse<String> response = client.send(request, BodyHandlers.ofString());
				
				System.out.println(response.statusCode());
				System.out.println(response.body());
				
				Pelicula creada = new Gson().fromJson(response.body(), Pelicula.class);
				System.out.println("Película creada:" + creada);
				
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	

}
