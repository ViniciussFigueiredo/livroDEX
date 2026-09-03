package com.example.bookfinder.principal;

import com.example.bookfinder.services.AutorService;
import com.example.bookfinder.services.ConsumoAPI;
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
        System.out.println(livro);

        System.out.println("Digite o nome do autor: ");
        var nomeAutor = leitura.nextLine();
        var autor = autorService.buscarLivrosDoAutor(nomeAutor);
        System.out.println(autor);
    }
}
