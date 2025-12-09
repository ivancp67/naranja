package ejercicio2.jdbc.app;


import ejercicio2.jdbc.model.Cliente;
import ejercicio2.jdbc.services.ClienteException;
import ejercicio2.jdbc.services.ClienteService;

public class App {
	public static void main(String[] args) {
		ClienteService service = new ClienteService();
		try {
			Cliente cliente = service.consultarCliente().get("MARILYN.ROSS@sakilacustomer.org");
			System.out.println(cliente);
		} catch (ClienteException e) {
			e.printStackTrace();
		}
		
	}
}
