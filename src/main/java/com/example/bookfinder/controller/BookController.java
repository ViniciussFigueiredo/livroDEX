package com.example.bookfinder.controller;

import com.example.bookfinder.model.Autor;
import com.example.bookfinder.model.Livro;
import com.example.bookfinder.services.AutorService;
import com.example.bookfinder.services.LivroService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class BookController {

    private final LivroService livroService = new LivroService();
    private final AutorService autorServicer = new AutorService();

    @GetMapping("/livros")
    public Livro buscarLivro (@RequestParam String nome) throws Exception{
        return livroService.buscarEProcessarLivro(nome);
    }

    @GetMapping ("/autores/obras")
    public List<Livro> buscarObras (@RequestParam String autor) throws Exception {
        return autorServicer.buscarLivrosDoAutor(autor);
    }

    @GetMapping("/autores/detalhes")
    public Autor infoAutor (@RequestParam String autor) throws Exception {
        return autorServicer.buscarInfoAutor(autor);
    }
}
