package io.cesa.libraryAPI;



import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing // faz as annotations de  date funcionarem
@SpringBootApplication
public class Application {
	public static void main(String[] args) {
		  SpringApplication.run(Application.class, args);
		}
}


