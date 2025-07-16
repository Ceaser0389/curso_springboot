package io.cesa.libraryAPI.service;

import io.cesa.libraryAPI.model.Autor;
import io.cesa.libraryAPI.model.GeneroLivro;
import io.cesa.libraryAPI.model.Livro;
import io.cesa.libraryAPI.repository.AutorRepository;
import io.cesa.libraryAPI.repository.LivroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

@Service
public class TrasacaoService {

    @Autowired
    private AutorRepository autorRepository;
    @Autowired
    private LivroRepository livroRepository;

    @Transactional
    public  void atualizacaoSemAtualizar(){
        var livro = livroRepository.findById(UUID
            .fromString("00057da1-8db8-4db8-b1a9-cf2e8c8cd3e1"))
            .orElse(null);

        livro.setDataPublicacao(LocalDate.of(2024, 6, 1));

        livroRepository.save(livro);
    }


    @Transactional
    public void executar(){
        // salva o autor
        Autor autor = new Autor();
        autor.setNome("Francisca");
        autor.setNacionalidade("Brasileira");
        autor.setDataNascimento(LocalDate.of(1970, 11 , 3));

        autorRepository.save(autor);

        //salva o livro
        Livro livro = new Livro();
        livro.setIsbn("90887-84874");
        livro.setPreco(BigDecimal.valueOf(100));
        livro.setGenero(GeneroLivro.FICCAO);
        livro.setTitulo("Roubo da casa assombrada 2");
        livro.setDataPublicacao(LocalDate.of(1998, 1, 2));

        livro.setAutor(autor);

        livroRepository.save(livro);

        if(autor.getNome().equals("José")){
            throw  new RuntimeException("rollback!");
        }
    }




}
