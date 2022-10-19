package br.org.serratec.biblioteca.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.org.serratec.biblioteca.entity.Aluno;
import br.org.serratec.biblioteca.entity.Emprestimo;

public interface EmprestimoRepository extends JpaRepository<Emprestimo, Integer>{

    public List<Emprestimo> findByAluno(Aluno aluno);
    
}
