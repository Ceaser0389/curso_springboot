package io.cesa.libraryAPI.repository;

import io.cesa.libraryAPI.model.Autor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface AutorRepository  extends JpaRepository<Autor, UUID> {

    List<Autor> findByNome(String nome);
    List<Autor> findByNacionalidade(String nacionalidade);
    List<Autor> findByNomeAndNacionalidade(String nome ,String nacionalidade);


}
