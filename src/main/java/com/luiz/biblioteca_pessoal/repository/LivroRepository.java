package com.luiz.biblioteca_pessoal.repository;

import com.luiz.biblioteca_pessoal.model.Livro;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LivroRepository extends JpaRepository<Livro, Long> {
}
