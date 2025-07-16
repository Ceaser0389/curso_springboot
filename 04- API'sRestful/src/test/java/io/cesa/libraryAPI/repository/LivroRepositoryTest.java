package io.cesa.libraryAPI.repository;

import io.cesa.libraryAPI.model.Autor;
import io.cesa.libraryAPI.model.GeneroLivro;
import io.cesa.libraryAPI.model.Livro;
import jakarta.persistence.Transient;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@SpringBootTest
class LivroRepositoryTest {
  
   @Autowired
   LivroRepository repository;
   @Autowired
   AutorRepository autorRepository;
   
   @Test
    void salvarTest(){
     Livro livro = new Livro();
     livro.setIsbn("90887-84874");
     livro.setPreco(BigDecimal.valueOf(100));
     livro.setGenero(GeneroLivro.FICCAO);
     livro.setTitulo("Ciência");
     livro.setDataPublicacao(LocalDate.of(1980, 1, 2));
     
     Autor autor = autorRepository
         .findById(UUID.fromString("55d20430-e3f1-4ef0-a487-48bd565e126b"))
         .orElse(null);
     
     livro.setAutor( new Autor());
     repository.save(livro);
   }
   
   
   @Test
   void salvarAutorELivroTest(){
     Livro livro = new Livro();
     livro.setIsbn("90887-84874");
     livro.setPreco(BigDecimal.valueOf(100));
     livro.setGenero(GeneroLivro.FICCAO);
     livro.setTitulo("Roubo da casa assombrada 2");
     livro.setDataPublicacao(LocalDate.of(1998, 1, 2));
     
     Autor autor = new Autor();
     autor.setNome("Tigão");
     autor.setNacionalidade("Brasileira");
     autor.setDataNascimento(LocalDate.of(1970, 11 , 3));
     
     autorRepository.save(autor);
     
     livro.setAutor(autor);
     
     repository.save(livro);
   }
   
   
   @Test
   void salvarCascateTest(){
     Livro livro = new Livro();
     livro.setIsbn("90887-84874");
     livro.setPreco(BigDecimal.valueOf(100));
     livro.setGenero(GeneroLivro.CIENCIA);
     livro.setTitulo("Ciência do Universo");
     livro.setDataPublicacao(LocalDate.of(1990, 1, 2));
     
     Autor autor = new Autor();
     autor.setNome("Gael");
     autor.setNacionalidade("Brasileira");
     autor.setDataNascimento(LocalDate.of(1970, 11 , 3));
     
     livro.setAutor(autor);
     
     repository.save(livro);
   }
   
   @Test
    void atulizarAutorDoLivro(){
      UUID id = UUID.fromString("5d1af41b-a93e-4804-9082-f745728bedab");
      var livroParaAtualizar = repository.findById(id).orElse(null);
      
      UUID idAutor = UUID.fromString("169120c7-13d8-48af-ac4d-427588c9c042");
      Autor maria = autorRepository.findById(idAutor).orElse(null);
     
      livroParaAtualizar.setAutor(maria);
      
      repository.save(livroParaAtualizar);
   }
   
   @Test
   void deletar(){
     UUID id = UUID.fromString("5d1af41b-a93e-4804-9082-f745728bedab");
     repository.deleteById(id);
   }
  
  @Test
  void deletarCascade(){
    UUID id = UUID.fromString("5b179d6c-bf98-4a8d-8e2a-98487ad05859");
    repository.deleteById(id);
  }
  
  @Test
  @Transactional
  void buscarLivroTest(){
     UUID id = UUID.fromString("1f9bf6d4-1110-4b3a-a43e-3c1aa2bcd72a");
     Livro livro = repository.findById(id).orElse(null);
     System.out.println("Livro: ");
     System.out.println(livro.getTitulo());
     
     System.out.println("Autor: ");
     System.out.println(livro.getAutor().getNome());
  }
  
  @Test
  void pesquisaPorTituloTest(){
    List<Livro> lista = repository.findByTitulo(" O Roubo da casa assombrada");
    lista.forEach(System.out::println);
  }

  @Test
  void pesquisaPorISBNTest(){
    List<Livro> lista = repository.findByIsbn(" 20887-84874 ");
    lista.forEach(System.out::println);
  }

  @Test
  void pesquisaPorTituloEPrecoTest(){
    var preco = BigDecimal.valueOf(204.00);
    String tituloPesquisa = "O roubo da casa assombrada";

    List<Livro> lista = repository.findByTituloAndPreco(tituloPesquisa, preco );
    lista.forEach(System.out::println);
  }

  @Test
  void listarLivrosComQueryJPQL(){
     var resultado = repository.listarTodosOrdenadoPorTituloAndPreco();
     resultado.forEach(System.out::println);
  }

  @Test
  void listarAutoresDosLivros(){
    var resultado = repository.listarAutoresDosLivros();
    resultado.forEach(System.out::println);
  }

  @Test
  void listarTitulosNaoRepetidosDosLivros(){
    var resultado = repository.listarAutoresDosLivros();
    resultado.forEach(System.out::println);
  }

  @Test
  void listarGenerosDeLivrosAutoresBrasileiros(){
    var resultado = repository.listarGenerosAutoresBrasileiros();
    resultado.forEach(System.out::println);
  }

  @Test
  void listarPorGeneroQueryParamTest(){
     var resultado = repository.findByGenero(GeneroLivro.FICCAO,"dataPublicacao");
     resultado.forEach(System.out::println);
  }

  @Test
  void listarPorGeneroPositionalParamTest(){
    var resultado = repository.findByGeneroPositionalParameters(GeneroLivro.FICCAO, "preco");
    resultado.forEach(System.out::println);
  }


  @Test
  void deletPorGeneroTest(){
     repository.deleteByGenero(GeneroLivro.CIENCIA);
  }

  @Test
  void updateDataPublicaoTest(){
    repository.updateDataPublicacao(LocalDate.of(2000, 1, 1));
  }

  
}