package sk.example.accountant;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringAccountantApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringAccountantApplication.class, args);
		
		System.out.println(" Yes I am running");
	}

}
