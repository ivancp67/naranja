package ceu.dam.ad;
import ceu.dam.ad.model.Cliente;
import ceu.dam.ad.model.Direccion;
import ceu.dam.ad.services.ClienteNotFoundException;
import ceu.dam.ad.services.ClienteService;

import java.util.ArrayList;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;


@SpringBootApplication
public class ProyectoMongoApplication {

	public static void main(String[] args) {
		ConfigurableApplicationContext context = SpringApplication.run(ProyectoMongoApplication.class, args);
		ClienteService service = context.getBean(ClienteService.class);
		
		Cliente cliente = new Cliente();
		cliente.setNombre("Manolo");
		cliente.setEdad(32);
		cliente.setApellido("Kuko Efigenio");
		cliente.setDirecciones(new ArrayList<>());
		for (int i = 0; i < 3; i++) {
			cliente.getDirecciones().add(new Direccion());
			cliente.getDirecciones().get(i).setCiudad("Ciudad" + i);
			cliente.getDirecciones().get(i).setCp("0000" + i);
		}
		
		System.out.println("Vamos a probar a crear un cliente" + cliente);
		Cliente nuevoCliente = service.crearCliente(cliente);
		System.out.println("Cliente creado: " + cliente);
		System.out.println("Vamos a probar a consultar un cliente con id: " + nuevoCliente);
		try {
			Cliente clienteConsultado = service.consultarCliente(nuevoCliente.getId());
			System.out.println(clienteConsultado);
		} catch (ClienteNotFoundException e) {
			System.out.println("No existe!!");
		}
		
		
	}

}
