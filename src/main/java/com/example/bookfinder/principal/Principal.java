package com.example.bookfinder.principal;

import com.example.bookfinder.services.AutorService;
import com.example.bookfinder.services.LivroService;

import java.util.Scanner;

public class Principal {
    Scanner leitura = new Scanner(System.in);
    LivroService livroService = new LivroService();
    AutorService autorService = new AutorService();
    public void main () throws Exception {
        System.out.println("Digite o nome do livro: ");
        var nomeLivro = leitura.nextLine();
        var livro = livroService.buscarEProcessarLivro(nomeLivro);
        if (livro != null) {
            System.out.println("Nome do livro: " + livro.getTitulo());
            System.out.println("Ano de lançamento: " + livro.getAnoDeLancamento());
            System.out.println("Numero de paginas: " + livro.getNumeroDePaginas());
            System.out.println("Autor: " + livro.getNomeAutor());
        }

        System.out.println("Encontre o livro pelo nome do autor: ");
        var buscarLivroPorAutor = leitura.nextLine();
        var livrosAutor = autorService.buscarLivrosDoAutor(buscarLivroPorAutor);
        System.out.println("As 10 Obras mais famosas de " + buscarLivroPorAutor);
        if (livrosAutor != null) {
            livrosAutor.forEach(System.out::println);
        }

        System.out.println("Digite o nome do autor: ");
        var nomeAutor = leitura.nextLine();
        var autor = autorService.buscarInfoAutor(nomeAutor);
        if (autor != null) {
            System.out.println("Nome do autor: " + autor.getNomeAutor());
            System.out.println("Data de nascimento: " + autor.getAnoDeNascimento());
            System.out.println("Data de falecimento: " + autor.getAnoDeFalecimento());
            System.out.println("Biografia: " + autor.getBiografia());
            System.out.println("Quantidade de obras publicadas: " + autor.getNumeroDeObras());
            System.out.println("Livro mais famoso: " + autor.getMelhorLivro());
        }
    }
}
