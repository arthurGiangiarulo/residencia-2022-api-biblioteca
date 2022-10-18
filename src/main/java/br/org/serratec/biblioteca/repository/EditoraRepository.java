package br.org.serratec.biblioteca.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.org.serratec.biblioteca.entity.Editora;

public interface EditoraRepository extends JpaRepository<Editora, Integer>{
        
}
