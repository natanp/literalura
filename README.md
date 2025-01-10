
# LiterAlura - Challenge Alura 2024

Aplicação em Java parte do Desafio LiterAlura do curso de Spring. Utiliza a API [Gutendex](https://gutendex.com/) para buscar livros pelo título ou nome do Autor e cadastrar os livros buscados em um banco de dados PostgreSQL. Em seguida são feitas algumas operações citadas abaixo:




## Funcionalidades

- Consultar API Gutendex para buscar livros pelo título ou nome do Autor.
- Cadastrar livros e autores buscados na API em um banco de dados PostgreSQL Local.
- Listar todos livros cadastrados no banco de dados.
- Listar todos autores cadastrados no banco de dados.
- Consultar no banco de dados quais autores estavam vivos em um determinado ano fornecido via console pelo usuário.
- Consultar no banco de dados livros filtrados por idioma fornecido via console pelo usuário.
- Gerar um ranking de top10 livros com mais downloads.



## Como Usar

1. Clone este repositório:
   ```bash
   git clone https://github.com/natanp/literalura.git
   ```

2. Compile o código:

   Navegue até o diretório do projeto e execute o seguinte comando Maven:
   ```bash
   mvn clean package
   ```

3. Executar o JAR gerado na pasta target do projeto: 
   ```bash
   java -jar target/literalura-0.0.1-SNAPSHOT
   ```

4. Navegar pelas opções no console Java
   
   O programa solicitará que você escolha as opções entre as citadas em [Funcionalidades]() e irá exibir os resultados no console.
