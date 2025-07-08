package io.cesa.libraryAPI.repository;

import io.cesa.libraryAPI.model.Autor;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;


@SpringBootTest
public class AutorRepositoryTest {
  
   @Autowired
   AutorRepository repository;
   
   @Test
   public void salvarTest(){
       Autor autor = new Autor();
       autor.setNome("Maria");
       autor.setNacionalidade("Brasileira");
       autor.setDataNascimento(LocalDate.of(1951, 1, 31));
       
       var autorSalvo = repository.save(autor);
       System.out.println("Autor Salvo: " + autorSalvo);
   }
   
  
   
   
   
  
}
