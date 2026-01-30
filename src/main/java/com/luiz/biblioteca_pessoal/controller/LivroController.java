package com.luiz.biblioteca_pessoal.controller;

import com.luiz.biblioteca_pessoal.model.Livro;
import com.luiz.biblioteca_pessoal.repository.LivroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/livros")
public class LivroController {

    @Autowired
    private LivroRepository livroRepository;

    @GetMapping
    public List<Livro> listarLivros() {
        return livroRepository.findAll();
    }

    @PostMapping
    public Livro adicionarLivro(@RequestBody Livro livro) {
        return livroRepository.save(livro);
    }

    @PutMapping("/{id}")
    public Livro atualizarLivro(@PathVariable Long id, @RequestBody Livro dadosNovos) {
        return livroRepository.findById(id)
                .map(livroExistente -> {
                    // Atualiza apenas os dados que vêm do formulário
                    livroExistente.setTitulo(dadosNovos.getTitulo());
                    livroExistente.setAutor(dadosNovos.getAutor());
                    livroExistente.setCapa(dadosNovos.getCapa());

                    // Se quiser atualizar nota e status também, pode adicionar aqui:
                    // livroExistente.setNota(dadosNovos.getNota());
                    // livroExistente.setStatus(dadosNovos.getStatus());

                    return livroRepository.save(livroExistente);
                })
                .orElse(null);
    }

    @DeleteMapping("/{id}")
    public void deletarLivro(@PathVariable Long id) {
        livroRepository.deleteById(id);
    }
}