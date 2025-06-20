package io.cesa.produtosapi.repository;

import io.cesa.produtosapi.model.Produto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProdutoRepository  extends JpaRepository<Produto,String> {
}
