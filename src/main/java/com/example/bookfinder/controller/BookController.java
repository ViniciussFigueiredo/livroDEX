package com.example.bookfinder.controller;

import com.example.bookfinder.model.Autor;
import com.example.bookfinder.model.Livro;
import com.example.bookfinder.services.AutorService;
import com.example.bookfinder.services.LivroService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:5174")
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

    @GetMapping("/busca")
    public ResponseEntity<?> buscarGenerico(@RequestParam String termo) {
        try {
            Autor autor = autorServicer.buscarInfoAutor(termo);
            if (autor != null) {
                return ResponseEntity.ok(Map.of("tipo", "autor", "dados", autor));
            }
        } catch (Exception e) {
            // Se der erro/não encontrar o autor
        }

        try {
            Livro livro = livroService.buscarEProcessarLivro(termo);
            if (livro != null) {
                return ResponseEntity.ok(Map.of("tipo", "livro", "dados", livro));
            }
        } catch (Exception e) {
            // Se der erro/não encontrar o livro, ignora e avança para o autor
        }

        return ResponseEntity.notFound().build();
    }
}
