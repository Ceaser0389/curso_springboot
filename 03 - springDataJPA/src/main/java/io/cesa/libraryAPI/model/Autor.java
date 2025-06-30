package io.cesa.libraryAPI.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "autor", schema = "public") // schema public não precisa por
@Getter  // tempo de compilação
@Setter
public class Autor {
  
  @Id
  @Column(name="id")  // n é obrig
  @GeneratedValue(strategy = GenerationType.AUTO)
  private UUID id;

  @Column(name = "nome",length = 100, nullable = false) // opcional esse mapeamento
  private String nome;
  
  @Column(name = "data_nascimento", nullable = false)
  private LocalDate dataNascimento;
  
  @Column(name = "nacionalidade", length = 50, nullable = false)
  private String nacionalidade;
  
  @OneToMany(mappedBy = "autor")
  private List<Livro> livros;
  
  
  
  
}
