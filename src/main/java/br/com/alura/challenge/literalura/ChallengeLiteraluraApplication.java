package br.com.alura.challenge.literalura;

import br.com.alura.challenge.literalura.principal.Principal;
import br.com.alura.challenge.literalura.service.LivroService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ChallengeLiteraluraApplication implements CommandLineRunner {

	private final LivroService livroService;

    public ChallengeLiteraluraApplication(LivroService livroService) {
        this.livroService = livroService;
    }

    public static void main(String[] args) {
		SpringApplication.run(ChallengeLiteraluraApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Principal principal = new Principal(livroService);
		principal.exibirMenu();
	}
}
