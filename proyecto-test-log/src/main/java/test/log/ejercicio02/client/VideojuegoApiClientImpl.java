package test.log.ejercicio02.client;

import java.net.URI;

import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpRequest.BodyPublishers;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.util.Arrays;
import java.util.List;

import com.google.gson.Gson;

import test.log.ejercicio02.excepcions.ApiException;
import test.log.ejercicio02.excepcions.NotFoundException;
import test.log.ejercicio02.model.Videojuego;



public class VideojuegoApiClientImpl  implements VideojuegoApiClient{
	private String urlBase; // Este atributo contendrá la URL base a la que hacer las peticiones
	
	public VideojuegoApiClientImpl(String uuidUrl) { 
		// El constructor recibe el identificador que ha generado crudcrud.com para nuestro API y construye la URL base
		urlBase = "https://crudcrud.com/api/" + uuidUrl + "/videojuego";
	}
	
	@Override
	public Videojuego findById(String id) throws NotFoundException, ApiException {
		try {
			URI url = URI.create(urlBase + "/" + id);
			HttpClient client = HttpClient.newHttpClient();
			HttpRequest request = HttpRequest.newBuilder(url).GET().build();
			
			HttpResponse<String> response = client.send(request, BodyHandlers.ofString());
			
			if(response.statusCode()==404) {
				throw new NotFoundException("No existe videojuego");
			}
			Gson gson = new Gson();
			Videojuego videojuego = gson.fromJson(response.body(), Videojuego.class);
			return videojuego;
			
		} catch (NotFoundException e) {
			throw e;
		}catch (Exception e) {
			throw new ApiException("Error al analizar petición al API", e);
		}
	}

	@Override
	public List<Videojuego> findByAñoPublicacion(Integer año) throws NotFoundException, ApiException {
		try {
			URI url = URI.create(urlBase);
			HttpClient client = HttpClient.newHttpClient();
			HttpRequest request = HttpRequest.newBuilder(url).GET().build();
			
			HttpResponse<String> response = client.send(request, BodyHandlers.ofString());
			Gson gson = new Gson();
			List<Videojuego> videojuegos = Arrays.asList(gson.fromJson(response.body(), Videojuego[].class));
			for (Videojuego videojuego : videojuegos) {
				if (videojuego.getAñoPublicacion() == año) {
					videojuegos.add(videojuego);
				}
			}
			if (videojuegos.isEmpty()) {
				throw new NotFoundException("No existen videojuegos en ese año indicado");
			}
			return videojuegos;
			
		} catch (NotFoundException e) {
			throw e;
		}catch (Exception e) {
			throw new ApiException("Error al analizar petición al API", e);
		}
	}

	@Override
	public String create(Videojuego videojuego) throws ApiException {
		try {
			URI url = URI.create(urlBase);
			HttpClient client = HttpClient.newHttpClient();
			Gson gson = new Gson();
			String json = gson.toJson(videojuego);
			HttpRequest request = HttpRequest.newBuilder(url).header("Content-Type", "application/json").POST(BodyPublishers.ofString(json)).build();
			HttpResponse<String> response = client.send(request, BodyHandlers.ofString());
			Videojuego videojuegoCreado = gson.fromJson(response.body(), Videojuego.class);
			return videojuegoCreado.get_id();
		} catch (Exception e) {
			throw new ApiException("Error al analizar petición al API", e);
		}
		
	}

	@Override
	public void update(Videojuego videojuego) throws NotFoundException, ApiException {
		try {
			URI url = URI.create(urlBase);
			HttpClient client = HttpClient.newHttpClient();
			videojuego.set_id(null);
			Gson gson = new Gson();
			String json = gson.toJson(videojuego);
			HttpRequest request = HttpRequest.newBuilder(url).header("Content-Type", "application/json").PUT(BodyPublishers.ofString(json)).build();
			HttpResponse<String> response = client.send(request, BodyHandlers.ofString());
			if(response.statusCode()==404) {
				throw new NotFoundException("No existe videojuego con ID indicado");
			}
			
		} catch (NotFoundException e) {
			throw e;
		}catch (Exception e) {
			throw new ApiException("Error al analizar petición al API", e);
		}
	}

	@Override
	public void delete(String id) throws NotFoundException, ApiException {
		try {
			URI url = URI.create(urlBase + id);
			HttpClient client = HttpClient.newHttpClient();
			HttpRequest request = HttpRequest.newBuilder(url).DELETE().build();
			
			HttpResponse<String> response = client.send(request, BodyHandlers.ofString());
			
			if(response.statusCode()==404) {
				throw new NotFoundException("No existe videojuego");
			}
			
		} catch (NotFoundException e) {
			throw e;
		}catch (Exception e) {
			throw new ApiException("Error al analizar petición al API", e);
		}
	}


}
