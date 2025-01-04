package br.com.alura.challenge.literalura.gutendexapi.response;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public record DadosResponse(@JsonAlias("results") List<DadosLivro> livros) {
}
