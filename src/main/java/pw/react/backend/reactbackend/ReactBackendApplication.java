package pw.react.backend.reactbackend;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import pw.react.backend.reactbackend.repo.UserRepository;

@SpringBootApplication
public class ReactBackendApplication implements CommandLineRunner{

	@Autowired
    UserRepository repository;
	
	public static void main(String[] args){
		SpringApplication.run(ReactBackendApplication.class, args);
	}

	@Override
	public void run(String... arg0) throws Exception {
	}
}
