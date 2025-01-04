package br.com.alura.challenge.literalura.repository;

import br.com.alura.challenge.literalura.model.Autor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AutorRepository extends JpaRepository<Autor,Long> {
    Autor findByNome(final String nome);
    List<Autor> findByAnoNascimentoLessThanEqualAndAnoFalecimentoGreaterThanEqualOrAnoFalecimentoIsNull(Integer anoMininoNascimento, Integer anoMaximoFalecimento);
}
