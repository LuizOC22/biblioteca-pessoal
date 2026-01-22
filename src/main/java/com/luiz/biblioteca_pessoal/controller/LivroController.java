package com.luiz.biblioteca_pessoal.controller;

import com.luiz.biblioteca_pessoal.model.Livro;
import com.luiz.biblioteca_pessoal.repository.LivroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.*;
import com.luiz.biblioteca_pessoal.service.LivroService;

import java.util.List;


@RestController
@RequestMapping("/livros")
public class LivroController {

    @Autowired
    private LivroRepository repository;

    @Autowired
    private LivroService service;

    @GetMapping
    public List<Livro> listar() {
        return repository.findAll();
    }

    @PostMapping
    public Livro cadastrar(@RequestBody Livro livro){
        return service.cadastrarComCapaGoogle(livro);
    }

    @DeleteMapping("/{id}")
    public void excluir(@PathVariable Long id){
        repository.deleteById(id);
    }

}
