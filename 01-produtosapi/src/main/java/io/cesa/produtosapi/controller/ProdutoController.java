package io.cesa.produtosapi.controller;

import io.cesa.produtosapi.model.Produto;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController  ///  marca para ser um controlador
@RequestMapping("produtos")  // mapeia url
public class ProdutoController {

  @PostMapping
  public Produto salvar ( @RequestBody Produto produto) {
    System.out.printf("Produto recebido" + produto);
    return produto;
  }







}
