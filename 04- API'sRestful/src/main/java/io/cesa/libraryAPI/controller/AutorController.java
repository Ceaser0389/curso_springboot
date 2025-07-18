package io.cesa.libraryAPI.controller;

import io.cesa.libraryAPI.controller.dto.AutorDTO;
import io.cesa.libraryAPI.model.Autor;
import io.cesa.libraryAPI.service.AutorService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

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

    @GetMapping("{id}")
    public ResponseEntity<AutorDTO> obterDetalhes(@PathVariable("id") String id){
        var idAutor = UUID.fromString(id);
        Optional<Autor> autorOptional = service.obterPorId(idAutor);
        if (autorOptional.isPresent()){
            Autor autor = autorOptional.get();
            AutorDTO dto = new AutorDTO(
                autor.getId(),
                autor.getNome(),
                autor.getDataNascimento(),autor.getNacionalidade());
            return ResponseEntity.ok(dto);
        }
        return  ResponseEntity.notFound().build();
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> deletar(@PathVariable("id") String id){
        var idAutor = UUID.fromString(id);
        Optional<Autor> autorOptional = service.obterPorId(idAutor);

        if (autorOptional.isEmpty()){
            return  ResponseEntity.notFound().build();
        }

        service.deletar(autorOptional.get());

        return  ResponseEntity.noContent().build();
    }

    @GetMapping
    public ResponseEntity<List<AutorDTO>> pesquisar(
       @RequestParam(value = "nome", required = false) String nome,
       @RequestParam( value = "nacionalidade", required = false) String nacionalidade){
       List<Autor> resultado =  service.pesquisa(nome, nacionalidade);
       List<AutorDTO> lista =  resultado
           .stream()
           .map(autor -> new AutorDTO(
               autor.getId(),
               autor.getNome(),
               autor.getDataNascimento(),
               autor.getNacionalidade() )
           ).collect(Collectors.toList());

       return  ResponseEntity.ok(lista);
    }



}

/*
    //ResponseEntity<> um obj represneta todos os dados qu vc pode retornar em uma resposta
 */