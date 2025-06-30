package io.cesa.libraryAPI.model;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.ManyToAny;

import javax.swing.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

@Entity
@Table(name= "livro")
@Data  // ela incorpora @Getter@Setter@ToSgring@Equals@RequiredCnstructor


public class Livro {
  
  @Id
  @Column(name = "id")
  @GeneratedValue(strategy = GenerationType.UUID)
  private UUID id;
  
  @Column(name = "isbn", length = 20, nullable = false)
  private Spring isbn;
  
  @Column(name = "titulo", length = 150, nullable = false)
  private String titulo;
  
  @Column(name = "data_publicacao")
  private LocalDate dataPublicacao;
  
  @Enumerated(EnumType.STRING)
  @Column(name = "genero", length = 30, nullable = false)
  private GeneroLivro genero;
  
  @Column(name = "preco", precision = 12, scale = 2)
  private BigDecimal preco;
  
  @ManyToAny
  @JoinColumn(name = "id_autor")
  private Autor autor;
  
  
  
  
}
