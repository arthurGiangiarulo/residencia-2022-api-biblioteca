package br.org.serratec.biblioteca.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.org.serratec.biblioteca.entity.Aluno;

public interface AlunoRepository extends JpaRepository<Aluno, Integer>{
    
}
    