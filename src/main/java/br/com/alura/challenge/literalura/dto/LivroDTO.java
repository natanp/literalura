package br.com.alura.challenge.literalura.dto;

import br.com.alura.challenge.literalura.gutendexapi.response.DadosLivro;
import br.com.alura.challenge.literalura.model.Livro;
import org.apache.logging.log4j.util.Strings;

public record LivroDTO(String titulo, String idioma, Integer numeroDownloads, AutorDTO autor) {
    public LivroDTO(Livro livro) {
        this(livro.getTitulo(), livro.getIdioma(), livro.getNumeroDownloads(), new AutorDTO(livro.getAutor()));
    }

    public LivroDTO(DadosLivro dadosLivro) {
        this( (dadosLivro.titulo() != null && dadosLivro.titulo().length() > 255) ? dadosLivro.titulo().substring(0, 255) : dadosLivro.titulo(),
                dadosLivro.idioma().stream().findFirst().orElse(Strings.EMPTY),
                dadosLivro.numeroDownloads(),
                dadosLivro.autores().stream().findFirst().map(AutorDTO::new)
                        .orElse(new AutorDTO("Autor anônimo", null, null)));
    }

    @Override
    public String toString() {
        return """
           ---- LIVRO ----
           Título: %s
           %s
           Idioma: %s
           Número de downloads: %s
           """.formatted(titulo, autor, idioma, numeroDownloads);
    }
}
