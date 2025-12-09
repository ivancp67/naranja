package ejercicio3.jdbc.services;

import java.sql.Connection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import ejercicio2.jdbc.dao.ClienteDAO;
import ejercicio2.jdbc.model.Cliente;
import ejercicio3.jdbc.dao.PagoDao;
import ejercicio3.jdbc.model.Pago;
import ejercicio2.jdbc.services.ClienteException;

public class PagosServices extends Service{

	private ClienteDAO clienteDao;
	private PagoDao pagoDao;
	
	public PagosServices() {
		clienteDao = new ClienteDAO();
		pagoDao = new PagoDao();
	}
	
	public Map<String, List<Pago>> consultarCliente() throws ClienteException{
		Map<String, List<Pago>> mapa = new HashMap<>();
		try (Connection conn = abrirConexionSakila()){
			List<Cliente> lista = clienteDao.findAll(conn);
			for (Cliente cliente : lista) {
				String email = cliente.getEmail();
				Integer id = cliente.getId();
				
				List<Pago> pagos = pagoDao.listarCliente(conn, id);
				
				mapa.put(email, pagos);
			}
		} catch (Exception e) {
			throw new ClienteException("Error consultando clientes", e);
		}
		return mapa;
	}
}
