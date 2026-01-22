package com.luiz.biblioteca_pessoal.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.luiz.biblioteca_pessoal.model.Livro;
import com.luiz.biblioteca_pessoal.repository.LivroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class LivroService {

    @Autowired
    private LivroRepository repository;

    public Livro cadastrarComCapaGoogle(Livro livro) {
        System.out.println("--- INICIANDO SERVIÇO DE CADASTRO ---");

        if (livro.getCapa() == null || livro.getCapa().isEmpty()) {
            System.out.println("1. O livro chegou sem capa. Buscando no Google...");
            String urlCapa = buscarCapaNoGoogle(livro.getTitulo());

            System.out.println("4. Capa encontrada: " + urlCapa);
            livro.setCapa(urlCapa);
        } else {
            System.out.println("1. O usuário já enviou uma capa. Pulando busca.");
        }

        System.out.println("5. Salvando no banco...");
        return repository.save(livro);
    }

    private String buscarCapaNoGoogle(String titulo) {
        try {
            String url = "https://www.googleapis.com/books/v1/volumes?q=" + titulo.replace(" ", "+");
            System.out.println("2. URL gerada para o Google: " + url);

            RestTemplate template = new RestTemplate();
            String respostaJson = template.getForObject(url, String.class);
            // System.out.println("Resposta bruta do Google: " + respostaJson); // Descomente se quiser ver o JSON todo

            ObjectMapper mapper = new ObjectMapper();
            JsonNode raiz = mapper.readTree(respostaJson);

            if (raiz.has("items")) {
                JsonNode livroEncontrado = raiz.path("items").get(0);
                JsonNode imagens = livroEncontrado.path("volumeInfo").path("imageLinks");

                if (imagens.has("thumbnail")) {
                    String linkFoto = imagens.path("thumbnail").asText();
                    System.out.println("3. Achei a foto no JSON: " + linkFoto);
                    return linkFoto;
                } else {
                    System.out.println("3. O livro foi achado, mas não tem campo 'thumbnail'.");
                }
            } else {
                System.out.println("3. O Google retornou 0 livros para esse título.");
            }
        } catch (Exception e) {
            System.out.println("ERRO CRÍTICO NA BUSCA: " + e.getMessage());
            e.printStackTrace(); // Isso vai mostrar o erro vermelho detalhado
        }

        System.out.println("3. Retornando imagem padrão (erro ou não encontrado).");
        return "https://placehold.co/128x190/ccc/777?text=Sem+Capa";
    }
}