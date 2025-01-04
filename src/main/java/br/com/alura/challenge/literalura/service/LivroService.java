package br.com.alura.challenge.literalura.service;

import br.com.alura.challenge.literalura.dto.AutorDTO;
import br.com.alura.challenge.literalura.dto.LivroDTO;
import br.com.alura.challenge.literalura.gutendexapi.ConsumoAPIGutendex;
import br.com.alura.challenge.literalura.gutendexapi.response.DadosResponse;
import br.com.alura.challenge.literalura.model.Autor;
import br.com.alura.challenge.literalura.model.Livro;
import br.com.alura.challenge.literalura.repository.AutorRepository;
import br.com.alura.challenge.literalura.repository.LivroRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LivroService {

    private final LivroRepository livroRepository;
    private final AutorRepository autorRepository;
    private final ConverteDados conversorDados = new ConverteDados();

    public LivroService(LivroRepository livroRepository, AutorRepository autorRepository) {
        this.livroRepository = livroRepository;
        this.autorRepository = autorRepository;
    }

    public List<LivroDTO> listarLivros() {
        return livroRepository.findAll().stream().map(LivroDTO::new).toList();
    }

    public List<AutorDTO> listarAutores() {
        return autorRepository.findAll().stream().map(AutorDTO::new).toList();
    }

    public void buscarLivroPeloTitulo(String titulo){
        DadosResponse dadosRetornados = conversorDados.obterDados(ConsumoAPIGutendex.buscarLivroPeloTitulo(titulo), DadosResponse.class);
        dadosRetornados.livros().forEach(dadosLivro -> {
            LivroDTO livroDTO = new LivroDTO(dadosLivro);
            Autor autor = autorRepository.findByNome(livroDTO.autor().nome());
            if (autor == null) {
                autor = new Autor(livroDTO.autor().nome(),
                        livroDTO.autor().anoNascimento(),
                        livroDTO.autor().anoFalecimento());
                autorRepository.save(autor);
            }
            Livro livro = new Livro(livroDTO);
            livro.setAutor(autor);
            livroRepository.save(livro);
            System.out.println(livro);
        });
    }

    public List<AutorDTO> listarAutoresVivosNoAno(Integer ano) {
        return autorRepository.findByAnoNascimentoLessThanEqualAndAnoFalecimentoGreaterThanEqualOrAnoFalecimentoIsNull(ano,ano).stream()
                .map(AutorDTO::new)
                .toList();
    }

    public List<LivroDTO> listarLivrosPeloIdioma(String idioma) {
        return livroRepository.findByIdiomaIgnoreCase(idioma).stream()
                .map(LivroDTO::new)
                .toList();
    }
}
