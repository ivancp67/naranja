package ceu.dam.ad;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import ceu.dam.ad.model.perros.Domicilio;
import ceu.dam.ad.model.perros.Perro;
import ceu.dam.ad.model.perros.Persona;
import ceu.dam.ad.service.NotFoundException;
import ceu.dam.ad.service.PerrosService;

@SpringBootApplication
public class DemoSpringPerros2Application {

	public static void main(String[] args) {
		ConfigurableApplicationContext context = SpringApplication.run(DemoSpringPerros2Application.class, args);
		
		PerrosService service = context.getBean(PerrosService.class);
		
		try {
			Persona persona = new Persona();
			persona.setDni("4563278785H");
			persona.setNombre("Manolo");
			persona.setPerros(new ArrayList<Perro>());
			for (int i = 0; i <= 3; i++) {
				Perro p = new Perro();
				p.setNombre("Perro " + i);
				p.setNumChip(i+10000+"A");
				p.setRaza("Chucho");
				p.setVacunado(true);
				persona.getPerros().add(p);
			}
			
			service.createPersona(persona);
			
			Persona personaCreada = service.consultarPersona(persona.getIdPersona());
			System.out.println(personaCreada);
			System.out.println("Imprimo los perros aparte para que se vean bien");
			personaCreada.getPerros().forEach(System.out::println);
		} catch (Exception e) {
			System.out.println("El chucho no existe");
			e.printStackTrace();
		}
	}

}
