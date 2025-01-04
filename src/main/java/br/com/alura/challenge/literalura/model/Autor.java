package br.com.alura.challenge.literalura.model;

import br.com.alura.challenge.literalura.dto.AutorDTO;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "autores")
public class Autor extends Pessoa {

    @OneToMany(mappedBy = "autor",cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    private List<Livro> livros;

    public Autor() {
        super();
    }

    public Autor(String nomeAutor, Integer anoNascimento, Integer anoFalecimento) {
        super(nomeAutor,anoNascimento,anoFalecimento);
    }

    public Autor(AutorDTO autor) {
        super(autor.nome(),autor.anoNascimento(),autor.anoFalecimento());
    }

    @Override
    public String toString() {
        return "Autor: [%s] --- Data de nascimento: [%d] --- Data de falecimento: [%d]".formatted(getNome(),getAnoNascimento(), getAnoFalecimento());
    }

}
