package io.cesa.produtosapi.controller;

import io.cesa.produtosapi.model.Produto;
import io.cesa.produtosapi.repository.ProdutoRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController  // marca para ser um controlador
@RequestMapping("produtos")  // mapeia url
public class ProdutoController {

  private ProdutoRepository produtoRepository;

  public ProdutoController(ProdutoRepository produtoRepository) {
    this.produtoRepository = produtoRepository;
  }

  @PostMapping
  public Produto salvar ( @RequestBody Produto produto) {
    System.out.printf("Produto recebido" + produto);

    var id = UUID.randomUUID().toString();
    produto.setId(id);

    produtoRepository.save(produto);
    return produto;
  }

  @GetMapping("{id}")
  public Produto obterPorId(@PathVariable("id")  String id){
//    Optional<Produto> produto = produtoRepository.findById(id);
//    return  produto.isPresent() ? produto.get() : null;
    return  produtoRepository.findById(id).orElse(null);
  }

  @DeleteMapping("{id}")
  public void deletar( @PathVariable("id") String id){
    produtoRepository.deleteById(id);
  }




}
