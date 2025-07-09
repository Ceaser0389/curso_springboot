package io.cesa.libraryAPI.repository;

import io.cesa.libraryAPI.model.Autor;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

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
   
   @Test
   public void atulizarTest(){
      var id = UUID.fromString("7132b7b6-1d2c-4438-a9e2-2a406409913a");
      
      Optional<Autor> possivelAutor = repository.findById(id);
     
      if (possivelAutor.isPresent()){
          Autor autorEncontrado = possivelAutor.get();
          System.out.println("Dados do Autor: ");
          System.out.println(autorEncontrado);
          
          autorEncontrado.setDataNascimento(LocalDate.of(1960, 1, 30));
          
          repository.save(autorEncontrado);
      }
   }
   
   @Test
   public void listarTest(){
     List<Autor> lista = repository.findAll();
     lista.forEach(System.out::println);
   }
   
   @Test
   public void countTest(){
     System.out.println("Contagem de autores: " + repository.count());
   }
   
   @Test
   public void deletePorIdTest(){
      var id =   UUID.fromString("6b2b50dc-0a65-4c12-afd0-da5c89d30c0e");
      repository.deleteById(id);
   }
  
   @Test
   public void deleteTest(){
      var id =  UUID.fromString("7132b7b6-1d2c-4438-a9e2-2a406409913a");
      var maria = repository.findById(id).get();
      repository.delete(maria);
  }
  
  
  
   
}
