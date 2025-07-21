package io.cesa.libraryAPI.validator;

import io.cesa.libraryAPI.exceptions.RegistroDuplicadoException;
import io.cesa.libraryAPI.model.Autor;
import io.cesa.libraryAPI.repository.AutorRepository;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class AutorValidator {

    private AutorRepository repository;

    public AutorValidator(AutorRepository repository){
        this.repository = repository;
    }

    public  void validar(Autor autor){
        if (existeAutorCadastrado(autor)){
            throw new RegistroDuplicadoException("Autor já cadastrado!");
        }
    }

    private boolean existeAutorCadastrado(Autor autor){
        Optional<Autor> autorEncontrado = repository
            .findByNomeAndDataNascimentoAndNacionalidade(
                autor.getNome(), autor.getDataNascimento(), autor.getNacionalidade()
            );
        if(autor.getId() == null){
            return autorEncontrado.isPresent();
        }
        return  !autor.getId().equals(autorEncontrado.get().getId())
         && autorEncontrado.isPresent();

    }
}
