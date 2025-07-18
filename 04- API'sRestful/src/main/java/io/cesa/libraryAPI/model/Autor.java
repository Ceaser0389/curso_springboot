package io.cesa.libraryAPI.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "autor", schema = "public")
@Getter
@Setter
@ToString(exclude = {"livros"})
@EntityListeners(AuditingEntityListener.class)
/** ele vai observar as operações da class geriras anotations data e
 hora */
public class Autor {
  
  @Id
  @Column(name = "id")
  @GeneratedValue(strategy = GenerationType.UUID)
  private UUID id;
  
  @Column(name = "nome", length = 100, nullable = false)
  private String nome;
  
  @Column(name = "data_nascimento", nullable = false)
  private LocalDate dataNascimento;
  
  @Column(name = "nacionalidade",length = 50, nullable = false)
  private String nacionalidade;
  
  @OneToMany(mappedBy = "autor", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
  private List<Livro> livros;

  @CreatedDate  // coloca a data atual
  @Column(name = "data_cadastro")
  private LocalDateTime dataCadastro;

  @LastModifiedDate // atualiza com data atual no update
  @Column(name = "data_atualizacao")
  private LocalDateTime dataAtualizacao;

  @Column(name = "id_usuario")
  private  UUID idUsuario;

  
}
