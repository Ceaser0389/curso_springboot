package io.cesa.libraryAPI.repository;

import io.cesa.libraryAPI.service.TrasacaoService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestComponent;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
public class TransacoesTest {

    @Autowired
    TrasacaoService trasacaoService;

    /**
     * Commit -> conformar as alterações
     * rollback -> defazer as alteracoes
     */
    @Test
    void trasacaoSimples(){
        trasacaoService.executar();
        //salvar um livro , //
        // salvar o autor,
        // alugar o livro,
        // enviar email p locatário
        //notificar que o livro saiu da livraria
    }

    @Test
    void transacaoEstadoManged(){
        trasacaoService.atualizacaoSemAtualizar();
    }

}
