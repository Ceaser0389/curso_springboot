package io.cesa.libraryAPI.service;

import io.cesa.libraryAPI.exceptions.OperacaoNaoPermitidaException;
import io.cesa.libraryAPI.model.Autor;
import io.cesa.libraryAPI.repository.AutorRepository;
import io.cesa.libraryAPI.repository.LivroRepository;
import io.cesa.libraryAPI.validator.AutorValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor // pega o campo final gera um construtor
public class AutorService {


    private final AutorRepository repository;
    private final AutorValidator validator;
    private final LivroRepository livroRepository;

    // via construtor
//    public AutorService(AutorRepository repository,
//                        AutorValidator validator,
//                        LivroRepository livroRepository){
//        this.repository = repository;
//        this.validator = validator;
//        this.livroRepository = livroRepository;
//    }

    public Autor salvar(Autor autor){
        validator.validar(autor);
        return  repository.save(autor);
    }

    public void atualizar(Autor autor){
         if(autor.getId() == null){
             throw new IllegalArgumentException("Para atualizar, é necessario que o autor já  esteja salvo na base ");
         }
         validator.validar(autor);
         repository.save(autor);
    }


    public Optional<Autor> obterPorId(UUID id){

        return repository.findById(id);
    }

    public void deletar(Autor autor) {
        if (possuiLivro(autor)){
            throw  new OperacaoNaoPermitidaException(
                "Não é permitido excluir um Autor possui livros cadastrados!");
        }
        repository.delete(autor);
    }

    public List<Autor> pesquisa(String nome, String nacionalidade){
         if( nome != null && nacionalidade != null){
             return repository.findByNomeAndNacionalidade(nome, nacionalidade);
         }

         if(nome != null){
             return  repository.findByNome(nome);
         }

         if(nacionalidade != null){
             return repository.findByNacionalidade(nacionalidade);
         }

         return  repository.findAll();
    }

    public boolean possuiLivro(Autor autor){
        return  livroRepository.existsByAutor(autor);
    }

}
