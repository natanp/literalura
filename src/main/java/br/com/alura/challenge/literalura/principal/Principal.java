package br.com.alura.challenge.literalura.principal;

import br.com.alura.challenge.literalura.dto.AutorDTO;
import br.com.alura.challenge.literalura.dto.LivroDTO;
import br.com.alura.challenge.literalura.service.LivroService;

import java.util.Comparator;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class Principal {

    private final LivroService livroService;

    private final Scanner leitura = new Scanner(System.in);

    public Principal(LivroService livroService) {
        this.livroService = livroService;
    }

    public void exibirMenu() {
        var opcao = -1;

        while (opcao != 0) {
            var menu = """
                    *** Bem-vindo(a) ao LiterAlura ***
                        Escolha a opção desejada:
                    
                    1- Buscar livro pelo título
                    2- Listar livros cadastrados
                    3- Listar autores cadastrados
                    4- Listar autores vivos em determinado ano
                    5- Listar livros em um determinado idioma
                    6- Top 10 livros com mais downloads
                    
                    0 - Sair
                    """;

            System.out.println(menu);
            try {
                opcao = leitura.nextInt();
                leitura.nextLine();
                switch (opcao) {
                    case 1:
                        System.out.println("Digite o nome do livro que você deseja procurar:");
                        String titulo = leitura.nextLine();
                        livroService.buscarLivroPeloTitulo(titulo);
                        break;
                    case 2:
                        System.out.println("---- Livros cadastrados no banco de dados ----\n");
                        List<LivroDTO> livros = livroService.listarLivros();
                        if (livros.isEmpty()) {
                            System.out.println("Nenhum livro cadastrado ainda.");
                        } else {
                            livros.stream()
                                    .sorted(Comparator.comparing(LivroDTO::titulo))
                                    .forEach(System.out::println);
                        }
                        break;
                    case 3:
                        System.out.println("---- Autores cadastrados no banco de dados ----\n");
                        List<AutorDTO> autores = livroService.listarAutores();
                        if (autores.isEmpty()) {
                            System.out.println("Nenhum autor cadastrado ainda.");
                        } else {
                            autores.stream()
                                    .sorted(Comparator.comparing(AutorDTO::nome))
                                    .forEach(System.out::println);
                        }
                        break;
                    case 4:
                        System.out.println("Digite o ano que deseja consultar os autores vivos:");
                        var ano = leitura.nextInt();
                        leitura.nextLine();
                        System.out.printf("---- Autores vivos no ano %d ----%n",ano);
                        List<AutorDTO> autor = livroService.listarAutoresVivosNoAno(ano);
                        if (livroService.listarAutores().isEmpty()){
                            System.out.println("Nenhum autor cadastrado ainda.");
                        }
                        else if (autor.isEmpty()) {
                            System.out.println("Nenhum autor vivo nessa data.");
                        } else {
                            autor.stream()
                                    .sorted(Comparator.comparing(AutorDTO::nome))
                                    .forEach(System.out::println);
                        }
                        break;
                    case 5:
                        System.out.println("Escolha o idioma que deseja consultar os livros:  \nOpções: pt,en,fr");
                        var idioma = leitura.nextLine();
                        List<LivroDTO> livrosPeloIdioma = livroService.listarLivrosPeloIdioma(idioma);
                        if (livrosPeloIdioma.isEmpty()) {
                            System.out.println("Nenhum livro encontrado nesse idioma.");
                        } else {
                            livrosPeloIdioma.forEach(System.out::println);
                        }
                        break;
                    case 6:
                        System.out.println("---- TOP 10 Livros mais baixados ----\n");
                        List<LivroDTO> top10livros = livroService.listarLivros();
                        if (top10livros.isEmpty()) {
                            System.out.println("Nenhum livro cadastrado ainda.");
                        } else {
                            top10livros.stream()
                                    .sorted(Comparator.comparing(LivroDTO::numeroDownloads).reversed())
                                    .limit(10)
                                    .forEach(System.out::println);
                        }
                        break;
                    case 0:
                        System.out.println("Encerrando a aplicação!");
                        break;
                    default:
                        System.err.println("Opção inválida!");
                }
            } catch (InputMismatchException e) {
                opcao = -1;
                leitura.nextLine();
                System.err.println("Entrada inválida, por favor digite um número inteiro como opção!");
            }
        }
    }
}
