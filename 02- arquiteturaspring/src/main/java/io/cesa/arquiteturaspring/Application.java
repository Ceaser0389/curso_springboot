package io.cesa.arquiteturaspring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
  
  //  SpringApplicationBuilder builder =
   //       new SpringApplicationBuilder(Appendable.class);
    
   // builder.bannerMode(Banner.Mode.OFF);
   // builder.profiles("producao"); // pode subir varios perfils
    
   // builder.run(args);
    
    // contexto da aplicação já iniciada
   // ConfigurableApplicationContext applicationContext = builder.context();
    //var produtoRepository = applicationContext.getBean("produtoRepository");
    
  //  ConfigurableEnvironment environment = applicationContext.getEnvironment();
   // String aplicationName = environment.getProperty("spring.application.name");
   // System.out.println("Nome da aplicação: " + aplicationName);
    
  }

}
