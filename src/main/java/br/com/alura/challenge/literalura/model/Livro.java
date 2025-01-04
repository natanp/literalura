package br.com.alura.challenge.literalura.model;

import br.com.alura.challenge.literalura.dto.LivroDTO;
import jakarta.persistence.*;

@Entity
@Table(name = "livros")
public class Livro {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String titulo;

    private String idioma;

    private Integer numeroDownloads;

    @ManyToOne
    private Autor autor;

    public Livro(LivroDTO livroDTO) {
        this.titulo = livroDTO.titulo();
        this.idioma = livroDTO.idioma();
        this.numeroDownloads = livroDTO.numeroDownloads();
        this.autor = new Autor(livroDTO.autor());
    }

    public Livro() {}

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public Autor getAutor() {
        return autor;
    }

    public void setAutor(Autor autor) {
        this.autor = autor;
    }

    public String getIdioma() {
        return idioma;
    }

    public void setIdioma(String idioma) {
        this.idioma = idioma;
    }

    public Integer getNumeroDownloads() {
        return numeroDownloads;
    }

    public void setNumeroDownloads(Integer numeroDownloads) {
        this.numeroDownloads = numeroDownloads;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    @Override
    public String toString() {
        return """
           ---- LIVRO ----
           Título: %s
           Autor: %s
           Idioma: %s
           Número de downloads: %s
           """.formatted(titulo, autor, idioma, numeroDownloads);
    }
}
