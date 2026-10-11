package com.example.bookfinder.controller;

import com.example.bookfinder.model.Autor;
import com.example.bookfinder.model.Livro;
import com.example.bookfinder.services.AutorService;
import com.example.bookfinder.services.LivroService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
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

    @GetMapping("/busca/sugestoes")
    public List<Map<String, Object>> buscarSugestoes(
            @RequestParam String termo) throws Exception {

        if (termo == null || termo.trim().length() < 2) {
            return List.of();
        }

        List<Map<String, Object>> sugestoes = new ArrayList<>();

        try {
            Autor autor = autorServicer.buscarInfoAutor(termo);

            if (autor != null) {
                sugestoes.add(Map.of(
                        "tipo", "autor",
                        "nome", autor.getNomeAutor(),
                        "fotoUrl", autor.getFotos()
                ));
            }
        } catch (Exception e) {
            System.err.println("Não foi possível buscar o autor: " + e.getMessage());
        }

        List<Livro> livros = livroService.buscarSugestoes(termo);

        for (Livro livro : livros) {
            Map<String, Object> item = new HashMap<>();
            item.put("tipo", "livro");
            item.put("chave", livro.getChave());
            item.put("titulo", livro.getTitulo());
            item.put("autor", livro.getNomeAutor());
            item.put("capa", livro.getCapa());
            sugestoes.add(item);
        }

        return sugestoes.stream().limit(5).toList();
    }
}
