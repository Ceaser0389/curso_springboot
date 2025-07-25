package io.cesa.libraryAPI.controller;

import io.cesa.libraryAPI.controller.dto.AutorDTO;
import io.cesa.libraryAPI.controller.dto.ErroResposta;
import io.cesa.libraryAPI.exceptions.OperacaoNaoPermitidaException;
import io.cesa.libraryAPI.exceptions.RegistroDuplicadoException;
import io.cesa.libraryAPI.model.Autor;
import io.cesa.libraryAPI.service.AutorService;
import lombok.RequiredArgsConstructor;
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
@RequiredArgsConstructor
// http://localhost:8080/autores
public class AutorController {

    private final AutorService service;

//    public AutorController(AutorService service){
//        this.service = service;
//    }

    @PostMapping
    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Object> salvar(@RequestBody AutorDTO autor){
        try {
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
        } catch ( RegistroDuplicadoException e) {
            var erroDTO = ErroResposta.conflito(e.getMessage());
            return  ResponseEntity.status(erroDTO.status()).body(erroDTO);
        }
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
    public ResponseEntity<Object> deletar(@PathVariable("id") String id){
        try {
            var idAutor = UUID.fromString(id);
            Optional<Autor> autorOptional = service.obterPorId(idAutor);

            if (autorOptional.isEmpty()){
                return  ResponseEntity.notFound().build();
            }

            service.deletar(autorOptional.get());

            return  ResponseEntity.noContent().build();
        } catch (OperacaoNaoPermitidaException e) {
             var erroResposta = ErroResposta.respostaPadrao(e.getMessage());
             return ResponseEntity.status(erroResposta.status()).body(erroResposta);
        }
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

    @PutMapping("{id}")
    public ResponseEntity<Object>atualizar(
        @PathVariable("id") String id , @RequestBody AutorDTO dto){

        try {
            var idAutor = UUID.fromString(id);
            Optional<Autor> autorOptional = service.obterPorId(idAutor);

            if (autorOptional.isEmpty()){
                return  ResponseEntity.notFound().build();
            }

            var autor = autorOptional.get();
            autor.setNome(dto.nome());
            autor.setNacionalidade(dto.nacionalidade());
            autor.setDataNascimento(dto.dataNascimento());

            service.atualizar(autor);

            return ResponseEntity.noContent().build();
        } catch (RegistroDuplicadoException e) {
            var erroDTO = ErroResposta.conflito(e.getMessage());
            return  ResponseEntity.status(erroDTO.status()).body(erroDTO);
        }
    }

}

/*
    //ResponseEntity<> um obj represneta todos os dados qu vc pode retornar em uma resposta
 */