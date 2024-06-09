package com.alura.challengeliteralura.repository;

import com.alura.challengeliteralura.model.Autor;
import com.alura.challengeliteralura.model.Idiomas;
import com.alura.challengeliteralura.model.Livro;
import org.springframework.data.domain.Example;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface LivrosRepository extends JpaRepository<Livro, Long> {

    @Query("select a from Autor a WHERE a.nome = :name")
    Optional<Autor> encontarAutor(String name);

    @Query(value = "SELECT * FROM livros l WHERE :idioma = ANY (l.idiomas::varchar[])", nativeQuery = true)
    List<Livro> procurarLivroPorIdioma(String idioma);
}
