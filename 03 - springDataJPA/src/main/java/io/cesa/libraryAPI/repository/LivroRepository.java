package io.cesa.libraryAPI.repository;

import io.cesa.libraryAPI.model.Autor;
import io.cesa.libraryAPI.model.Livro;
import org.springframework.data.jpa.repository.JpaRepository;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

public interface LivroRepository extends JpaRepository<Livro, UUID> {

  //  QUERY METHOD
  // select * from livro where id _autor = id
  List<Livro> findByAutor(Autor autor);

  // select * from livro where titulo = titulo
  List<Livro> findByTitulo(String titulo);

  List<Livro> findByIsbn(String isbn);

  List<Livro> findByTituloAndPreco(String titulo, BigDecimal preco);


}
