package com.luiz.biblioteca_pessoal.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import org.springframework.cglib.core.Local;

import java.time.LocalDate;

@Entity
public class Indicacao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String nomeAmigo;

    @NotBlank
    private String motivo;

    private String nomeLivro;

    private LocalDate dataIndicacao;

    public Indicacao() {
        this.dataIndicacao = LocalDate.now();
    }

    public LocalDate getDataIndicacao() {
        return dataIndicacao;
    }

    public void setDataIndicacao(LocalDate dataIndicacao) {
        this.dataIndicacao = dataIndicacao;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public @NotBlank String getMotivo() {
        return motivo;
    }

    public void setMotivo(@NotBlank String motivo) {
        this.motivo = motivo;
    }

    public @NotBlank String getNomeAmigo() {
        return nomeAmigo;
    }

    public void setNomeAmigo(@NotBlank String nomeAmigo) {
        this.nomeAmigo = nomeAmigo;
    }

    public String getNomeLivro() {
        return nomeLivro;
    }

    public void setNomeLivro(String nomeLivro) {
        this.nomeLivro = nomeLivro;
    }
}
