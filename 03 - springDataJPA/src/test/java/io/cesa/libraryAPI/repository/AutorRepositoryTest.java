package io.cesa.libraryAPI.repository;

import io.cesa.libraryAPI.model.Autor;
import io.cesa.libraryAPI.model.GeneroLivro;
import io.cesa.libraryAPI.model.Livro;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@SpringBootTest
public class AutorRepositoryTest {
  
   @Autowired
   AutorRepository repository;
   
   @Autowired
   LivroRepository livroRepository;
   
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
  

  @Test
  void salvarAutorComLivroTest(){
    Autor autor = new Autor();
    autor.setNome("Leon");
    autor.setNacionalidade("Americano");
    autor.setDataNascimento(LocalDate.of(1970, 1, 31));
    
    Livro livro = new Livro();
    livro.setIsbn("20887-84874");
    livro.setPreco(BigDecimal.valueOf(204));
    livro.setGenero(GeneroLivro.FANTASIA);
    livro.setTitulo(" O Roubo da casa assombrada");
    livro.setDataPublicacao(LocalDate.of(1990, 1, 2));
    livro.setAutor(autor);
    
    Livro livro2 = new Livro();
    livro2.setIsbn("35187-84874");
    livro2.setPreco(BigDecimal.valueOf(305));
    livro2.setGenero(GeneroLivro.MISTERIO);
    livro2.setTitulo(" O Roubo da casa assombrada 2");
    livro2.setDataPublicacao(LocalDate.of(1990, 1, 2));
    livro2.setAutor(autor);
    
    autor.setLivros(new ArrayList<>());
    autor.getLivros().add(livro);
    autor.getLivros().add(livro2);
    
    repository.save(autor);
    livroRepository.saveAll(autor.getLivros());
  }
  
  @Test
  void listarLivrosAutor(){
      var id =  UUID.fromString("55d20430-e3f1-4ef0-a487-48bd565e126b");
      var autor = repository.findById(id).get();
      // buscar os livros do autor

      List<Livro> livrosLista = livroRepository.findByAutor(autor);
      autor.setLivros(livrosLista);

      autor.getLivros().forEach(System.out::println);
  }
   
}
