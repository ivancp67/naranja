package ceu.dam.ad;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import ceu.dam.ad.test.Test;

@SpringBootApplication
public class Questions2ApiServerApplication {

	public static void main(String[] args) {
		try {
			
		ConfigurableApplicationContext context = SpringApplication.run(Questions2ApiServerApplication.class, args);
		Test test = context.getBean(Test.class);
		test.test();
		
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
