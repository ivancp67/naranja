package ceu.dam.ad;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import ceu.dam.ad.test.Test;

@SpringBootApplication
public class StudentsApiServerApplication {

	public static void main(String[] args) {
		ConfigurableApplicationContext context = SpringApplication.run(StudentsApiServerApplication.class, args);
		Test test = context.getBean(Test.class);
		test.test();
	}

}
