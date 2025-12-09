package ceu.dam.ad.ejerciciosTema2.avanzado;

import org.springframework.boot.SpringApplication;


import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import ceu.dam.ad.ejerciciosTema2.avanzado.ejercicio3.test.Test3;

@SpringBootApplication
public class App {

	public static void main(String[] args) {
		ConfigurableApplicationContext context = SpringApplication.run(App.class, args);
		/*
		Test1 t1 = context.getBean(Test1.class);
		t1.test();
		
		Test2 t2 = context.getBean(Test2.class);
		t2.test();
		*/
		
		Test3 t3 = context.getBean(Test3.class);
		t3.test();
	}

}
