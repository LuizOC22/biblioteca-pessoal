package com.luiz.biblioteca_pessoal.controller;

import com.luiz.biblioteca_pessoal.model.Indicacao;
import com.luiz.biblioteca_pessoal.repository.IndicacaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/indicacoes")
public class IndicacaoController {

    @Autowired
    private IndicacaoRepository repository;

    @GetMapping
    public List<Indicacao> Listar(){
        return repository.findAll();
    }

    @PostMapping
    public Indicacao indicar (@RequestBody Indicacao indicacao) {
        return repository.save(indicacao);
    }

}
