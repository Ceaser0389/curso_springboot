package io.cesa.libraryAPI.controller;

import io.cesa.libraryAPI.controller.dto.AutorDTO;
import io.cesa.libraryAPI.service.AutorService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("autores")
// http://localhost:8080/autores
public class AutorController {

    private final AutorService service;

    public AutorController(AutorService service){
        this.service = service;
    }

    @PostMapping
    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Void> salvar(@RequestBody AutorDTO autor){
        var autorEntidade = autor.mapearParaAutor();
        service.salvar(autorEntidade);

        /**
         *  classe que cria uma URI, ela pega os dados da requsição atuação e cria uma
         *  nova URL
         *  http://localhost:8080/autores , ela pega url e add um id para esta entidade
         * @
         */
        URI location = ServletUriComponentsBuilder
            .fromCurrentRequest()
            .path("/{id}")
            .buildAndExpand(autorEntidade.getId())
            .toUri();
        return  ResponseEntity.created(location).build();
    }




}

/*
    //ResponseEntity<> um obj represneta todos os dados qu vc pode retornar em uma resposta
 */