package br.org.serratec.biblioteca.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.org.serratec.biblioteca.entity.Editora;
import br.org.serratec.biblioteca.entity.Livro;

public interface LivroRepository extends JpaRepository<Livro, Integer>{
    
    public List<Livro> findByEditora(Editora editora);

}
