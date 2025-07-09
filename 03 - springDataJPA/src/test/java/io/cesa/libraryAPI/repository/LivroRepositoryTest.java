package io.cesa.libraryAPI.repository;

import io.cesa.libraryAPI.model.Autor;
import io.cesa.libraryAPI.model.GeneroLivro;
import io.cesa.libraryAPI.model.Livro;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.time.LocalDate;
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
     livro.setTitulo("UFO");
     livro.setDataPublicacao(LocalDate.of(1980, 1, 2));
     
     Autor autor = autorRepository
         .findById(UUID.fromString("7132b7b6-1d2c-4438-a9e2-2a406409913a"))
         .orElse(null);
     
     livro.setAutor(autor);
     
     repository.save(livro);
     
     
     
   }
   
   
   
}