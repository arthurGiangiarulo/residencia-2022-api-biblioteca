package br.org.serratec.biblioteca.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.org.serratec.biblioteca.entity.Livro;
import br.org.serratec.biblioteca.service.LivroService;

@RestController
@RequestMapping("/livros")
public class LivroController {
    @Autowired
    LivroService livroService;

    @GetMapping
    public ResponseEntity<List<Livro>> getAllLivros(){
        return new ResponseEntity<>(livroService.getAllLivros(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Livro> getLivroById(@PathVariable int id){
        Livro livro = livroService.getLivroById(id);
        if(livro == null){
            return new ResponseEntity<>(livro, HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(livro, HttpStatus.OK);
        }
    }

    @PostMapping
    public ResponseEntity<Livro> saveLivro(@RequestBody Livro livro) {
        return new ResponseEntity<>(livroService.saveLivro(livro), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Livro> updateLivro(@RequestBody Livro livro, @PathVariable int id) {
        return new ResponseEntity<>(livroService.updateLivro(livro, id), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Livro> deleteLivro(@PathVariable int id){
        Livro livro = livroService.getLivroById(id);
        if(livro == null){
            return new ResponseEntity<>(livro, HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(livroService.deleteLivro(id), HttpStatus.OK);
        }
    }
}