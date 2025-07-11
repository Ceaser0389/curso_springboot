package io.cesa.libraryAPI.repository;

import io.cesa.libraryAPI.model.Autor;
import io.cesa.libraryAPI.model.Livro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

/**
 * @see LivroRepositoryTest
 */
public interface LivroRepository extends JpaRepository<Livro, UUID> {

  //  QUERY METHOD
  // select * from livro where id _autor = id
  List<Livro> findByAutor(Autor autor);

  // select * from livro where titulo = titulo
  List<Livro> findByTitulo(String titulo);

  // select * from livro where isbn  = ?
  List<Livro> findByIsbn(String isbn);

  // select * from livro where titulo = ? or preco = ?
  List<Livro> findByTituloAndPreco(String titulo, BigDecimal preco);

  // select * from livro where titulo = ? or isbn = ?
  List<Livro> findByTituloOrIsbnOrderByTitulo(String titulo,String isbn);

  // select * from livro where data_publicacao between ? and ?
  List<Livro> findByDataPublicacaoBetween(LocalDate inicio, LocalDate fim);

  //JPQL -> referencia as entidades e as propriedades
  // selct l.* from livro as l order by l.titulo
  @Query("select l from Livro as l order by l.titulo, l.preco ")
  List<Livro> listarTodosOrdenadoPorTituloAndPreco(); // nomeia o metodo como vc quiser

  /**
   * select a.*
   * from livro l
   * join autor a on a.id = l.id_autor
   */
  @Query("select a from Livro l join l.autor a ")
  List<Autor> listarAutoresDosLivros();

  // select distinct l.* from livro l
  @Query("select distinct l.titulo from Livro l")
  List<String> listarNomesDiferentes();

  @Query("""
      select l.genero
      from Livro l
      join l.autor a
      where a.nacionalidade = 'Brasileira'
      order by l.genero
      """)
  List<String> listarGenerosAutoresBrasileiros();

}
