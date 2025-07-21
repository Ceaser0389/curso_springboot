package io.cesa.libraryAPI.controller.dto;

import io.cesa.libraryAPI.model.Autor;
import jakarta.validation.constraints.*;

import java.time.LocalDate;
import java.util.UUID;

public record AutorDTO(
    UUID id,

    @NotBlank(message = "campo obrigatorio")
    @Size(min = 2,max = 100, message = "campo fora do tamanho padrao")
    String nome,

    @NotNull(message = "campo obrigatorio")
    @Past(message = "Não pode ser uma data futura")
    LocalDate dataNascimento,

    @NotBlank(message = "campo obrigatorio")
    @Size(max = 50, min = 2, message = "campo fora do tamanho padrao")
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
