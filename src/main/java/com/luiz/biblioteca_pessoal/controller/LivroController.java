package com.luiz.biblioteca_pessoal.controller;

import com.luiz.biblioteca_pessoal.model.Livro;
import com.luiz.biblioteca_pessoal.repository.LivroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/livros")
public class LivroController {

    @Autowired
    private LivroRepository repository;

    @GetMapping
    public List<Livro> listar() {
        return repository.findAll();
    }

    @PostMapping
    public Livro cadastrar(@RequestBody Livro livro){
        return repository.save(livro);
    }

}
