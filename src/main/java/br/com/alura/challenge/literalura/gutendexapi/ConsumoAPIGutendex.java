package br.com.alura.challenge.literalura.gutendexapi;

import br.com.alura.challenge.literalura.exception.RequisicaoGutendexException;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class ConsumoAPIGutendex {

    private ConsumoAPIGutendex(){}

    private static final String URL_BASE_API = "https://gutendex.com/";
    private static final String BUSCA_LIVRO = URL_BASE_API + "books?search=%s";

    public static String buscarLivroPeloTitulo(String titulo){
        return criarRequisicao(getBuscaLivroUrl(titulo));
    }

    private static String criarRequisicao(String url) {
        HttpClient client = HttpClient.newBuilder()
                .followRedirects(HttpClient.Redirect.ALWAYS)
                .build();
        HttpResponse<String> response;
        try {
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(url))
                    .build();
            response = client
                    .send(request, HttpResponse.BodyHandlers.ofString());
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            throw new RequisicaoGutendexException("Erro na requisição a API Gutendex", e);
        } catch (IOException e) {
            throw new RequisicaoGutendexException("Erro na requisição a API Gutendex", e);
        }
        return response.body();
    }

    private static String getBuscaLivroUrl(String tituloLivro) {
        return String.format(BUSCA_LIVRO, tituloLivro.replace(" ","%20"));
    }
}
