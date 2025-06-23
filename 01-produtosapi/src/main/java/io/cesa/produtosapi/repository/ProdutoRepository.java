package io.cesa.produtosapi.repository;

import io.cesa.produtosapi.model.Produto;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProdutoRepository  extends JpaRepository<Produto,String> {

  List<Produto> findByNome(String nome);

}
