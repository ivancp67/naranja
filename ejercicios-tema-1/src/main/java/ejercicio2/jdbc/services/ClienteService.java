package ejercicio2.jdbc.services;

import java.sql.Connection;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import ejercicio2.jdbc.dao.ClienteDAO;
import ejercicio2.jdbc.model.Cliente;

public class ClienteService extends Service{
	
	private ClienteDAO dao;
	
	public ClienteService() {
		dao = new ClienteDAO();
	}

	public Map<String, Cliente> consultarCliente() throws ClienteException{
		try (Connection conn = abrirConexionSakila()){
			List<Cliente> lista = dao.findAll(conn);
			return lista.stream().collect(Collectors.toMap(c -> c.getEmail(), c -> c));
		} catch (Exception e) {
			throw new ClienteException("Error consultando clientes", e);
		}
	}

	
}
