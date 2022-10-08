package br.org.serratec.biblioteca.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.org.serratec.biblioteca.repository.LivroRepository;
import br.org.serratec.biblioteca.entity.Livro;

@Service
public class LivroService {
    @Autowired
    LivroRepository livroRepository;

    public List<Livro> getAllLivros(){
        return livroRepository.findAll();
    }

    public Livro getLivroById(int id){
        return livroRepository.findById(id).orElse(null);
    }

    public Livro saveLivro(Livro livro){
        return livroRepository.save(livro); 
    }

    public Livro updateLivro(Livro livro, int id){
        Livro livroExistenteNoBanco = getLivroById(id);
        livroExistenteNoBanco.setAllAtributos(livro);
        return livroRepository.save(livroExistenteNoBanco);
    }

    public Livro deleteLivro(int id){
        livroRepository.deleteById(id);
        return getLivroById(id);
    }
}
