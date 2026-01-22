package com.luiz.biblioteca_pessoal.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;

@Entity
public class Livro {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String titulo;

    @NotBlank
    private String autor;

    @Enumerated(EnumType.STRING)
    private StatusLeitura status;

    @Min(1) @Max(5)
    private Integer nota;

    private String resumo;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getTitulo() { return titulo; }
    public void setTitulo(String titulo) { this.titulo = titulo; }

    public String getAutor() { return autor; }
    public void setAutor(String autor) { this.autor = autor; }

    public StatusLeitura getStatus() { return status; }
    public void setStatus(StatusLeitura status) { this.status = status; }

    public Integer getNota() { return nota; }
    public void setNota(Integer nota) { this.nota = nota; }

    public String getResenha() { return resumo; }
    public void setResenha(String resenha) { this.resumo = resenha; }

}
