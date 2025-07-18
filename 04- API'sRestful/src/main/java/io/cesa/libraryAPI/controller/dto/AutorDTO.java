package io.cesa.libraryAPI.controller.dto;

import io.cesa.libraryAPI.model.Autor;

import java.time.LocalDate;
import java.util.UUID;

public record AutorDTO(
    UUID id,
    String nome,
    LocalDate dataNascimento,
    String nacionalidade
) {

    /**
     * metodo  que trasforma o DTO para uma entidade
     * aqui no caso entidade autor
     *
     */
    public  Autor  mapearParaAutor(){
        Autor autor = new Autor();
        autor.setNome(this.nome);
        autor.setDataNascimento(this.dataNascimento);
        autor.setNacionalidade(this.nacionalidade);
        return autor;
    }

}
