package ro.unibuc.hello;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import ro.unibuc.hello.repositories.ActionRepository;

import javax.annotation.PostConstruct;

@SpringBootApplication
public class HelloApplication {
	@Autowired
	private ActionRepository actionRepository;

	public static void main(String[] args) {
		SpringApplication.run(HelloApplication.class, args);
	}

	@PostConstruct
	public void runAfterObjectCreated() {

	}
}
