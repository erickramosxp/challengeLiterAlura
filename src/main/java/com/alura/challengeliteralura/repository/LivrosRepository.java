package com.alura.challengeliteralura.repository;

import com.alura.challengeliteralura.model.Livro;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LivrosRepository extends JpaRepository<Livro, Long> {
}
