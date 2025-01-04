package br.com.alura.challenge.literalura.dto;

import br.com.alura.challenge.literalura.gutendexapi.response.DadosAutor;
import br.com.alura.challenge.literalura.model.Autor;
import br.com.alura.challenge.literalura.model.Livro;


public record AutorDTO(String nome,
                       Integer anoNascimento,
                       Integer anoFalecimento) {
    public AutorDTO(Livro livro) {
        this(livro.getAutor().getNome(),livro.getAutor().getAnoNascimento(),livro.getAutor().getAnoFalecimento());
    }

    public AutorDTO(Autor autor) {
        this(autor.getNome(),autor.getAnoNascimento(),autor.getAnoFalecimento());
    }

    public AutorDTO(DadosAutor dadosAutor) {
        this(dadosAutor.nome(),dadosAutor.anoNascimento(),dadosAutor.anoFalecimento());
    }

    @Override
    public String toString() {
        return "Autor: [%s] --- Data de nascimento: [%d] --- Data de falecimento: [%d]".formatted(nome,anoNascimento, anoFalecimento);
    }
}
