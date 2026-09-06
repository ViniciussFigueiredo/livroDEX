# LivroDEX Back-end

<p align="center">
  <img src="https://img.shields.io/badge/Spring_Boot-F2F4F9?style=for-the-badge&logo=spring-boot&logoColor=31C653" alt="Spring Boot Badge"/>
  <img src="https://img.shields.io/badge/Java_17+-007396?style=for-the-badge&logo=java&logoColor=white" alt="Java Badge"/>
  <img src="https://img.shields.io/badge/Maven-C71A36?style=for-the-badge&logo=apache-maven&logoColor=white" alt="Maven Badge"/>
</p>

## 📖 Sobre o Projeto

O **LivroDEX** é uma API REST desenvolvida em Spring Boot que serve como o núcleo de processamento e integração para a aplicação Bookly. Sua principal função é intermediar a comunicação entre o front-end e a API pública da **Open Library**.

O objetivo do projeto é fornecer endpoints simplificados e tratados para que o usuário possa buscar informações sobre livros e autores, ajudando-o a tomar a melhor decisão de leitura.

Este projeto faz parte do desafio de desenvolvimento completo (Fullstack), integrando um back-end robusto com um front-end em React.

## 🛠️ Tecnologias Utilizadas

*   **Java 17+**: Linguagem de programação principal.
*   **Spring Boot 3.x**: Framework para criação da API REST.
*   **Maven**: Ferramenta de gerenciamento de dependências e automação de build.
*   **Jackson**: Biblioteca para desserialização e serialização de JSON.
*   **Spring Web**: Para criação de controllers e manipulação de requisições HTTP.
*   **CORS Configuration**: Configurado para permitir requisições do front-end local (`http://localhost:5173`).

## 🔌 Consumo de API Externa

A aplicação consome dados da **Open Library API**. Foram implementados tratamentos específicos para as particularidades desta API, tais como:

1.  **Paginação e Filtros**: Uso de parâmetros `fields` e `limit` no endpoint de busca (`/search.json`) para otimizar o recebimento de dados como o número de páginas (`number_of_pages_median`).
2.  **Tratamento de Dados Heterogêneos**: Lógica customizada no Java para ler o campo `bio` do autor, que pode retornar tanto uma String simples quanto um objeto JSON (Markdown), garantindo estabilidade na desserialização.

## 🚀 Como Executar o Projeto

### Pré-requisitos

*   Java JDK 17 ou superior instalado.
*   Maven instalado (ou usar o Maven Wrapper `./mvnw`).
*   IDE de sua preferência (IntelliJ IDEA, Eclipse, VS Code).

### Passos para execução

1.  **Clonar o repositório:**
    ```bash
    git clone [git@github.com:ViniciussFigueiredo/livroDEX.git](git@github.com:ViniciussFigueiredo/livroDEX.git)
    cd livroDEX
    ```

2.  **Compilar o projeto (Maven):**
    ```bash
    mvn clean install
    ```

3.  **Executar a aplicação:**
    *   Via IDE: Execute a classe principal `com.example.bookfinder.BookfinderApplication`.
    *   Via Terminal:
        ```bash
        mvn spring-boot:run
        ```

A aplicação iniciará por padrão na porta **8080**. Você pode acessar a API em `http://localhost:8080`.



## 📍 Endpoints da API

Abaixo estão os principais endpoints disponíveis na API:

| Método | Endpoint | Descrição | Parâmetros de Query |
| :--- | :--- | :--- | :--- |
| **GET** | `/api/livros` | Busca detalhes de um livro específico. | `nome`: Título do livro |
| **GET** | `/api/autores/detalhes` | Busca a biografia detalhada e infos de um autor. | `key`: OLID do autor (ex: OL21650A) |
| **GET** | `/api/autores/obras` | Busca as 10 obras mais famosas de um autor. | `key`: OLID do autor |

### Exemplo de Requisição (Livro)

`GET http://localhost:8080/api/livros?nome=Dom+Casmurro`

**Resposta (JSON):**
```json
{
  "titulo": "Dom Casmurro",
  "anoDeLancamento": 1899,
  "numeroPaginas": 256,
  "avaliacao": 4.8,
  "capaUrl": "[https://covers.openlibrary.org/b/id/8225266-L.jpg](https://covers.openlibrary.org/b/id/8225266-L.jpg)",
  "nomeAutor": "Machado de Assis",
  "autorKey": "OL21650A"
}
```

## 📝 Estrutura do Código

```Plaintext
src/main/java/com/example/bookfinder
 ├── BookfinderApplication.java  <-- Classe Principal
 ├── config
 │    └── WebConfig.java        <-- Configuração de CORS
 ├── controller
 │    ├── AutorController.java  <-- Endpoints de Autor
 │    └── LivroController.java  <-- Endpoint de Livro
 ├── model
 │    ├── Autor.java           <-- Classe de Domínio (Autor)
 │    └── Livro.java           <-- Classe de Domínio (Livro)
 ├── record
 │    ├── DadosAutor.java      <-- DTO para JSON da API (Autor)
 │    └── DadosLivro.java      <-- DTO para JSON da API (Livro)
 └── service
      ├── AutorService.java    <-- Lógica de consumo da API OpenLibrary (Autor)
      └── LivroService.java    <-- Lógica de consumo da API OpenLibrary (Livro)
```
## ✒️ Autor

Desenvolvido por **Vinícius Santos Figueiredo**.
