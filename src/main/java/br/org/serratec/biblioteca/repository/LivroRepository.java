package br.org.serratec.biblioteca.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.org.serratec.biblioteca.entity.Livro;

public interface LivroRepository extends JpaRepository<Livro, Integer>{
    
}
