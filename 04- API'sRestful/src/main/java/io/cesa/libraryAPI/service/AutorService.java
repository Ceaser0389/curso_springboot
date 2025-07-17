package io.cesa.libraryAPI.service;

import io.cesa.libraryAPI.model.Autor;
import io.cesa.libraryAPI.repository.AutorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AutorService {


    private final AutorRepository repository;

    public AutorService(AutorRepository repository){
        this.repository = repository;
    }

    public Autor salvar(Autor autor){
        return  repository.save(autor);
    }

}
