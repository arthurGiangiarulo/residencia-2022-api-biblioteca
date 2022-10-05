package br.org.serratec.biblioteca.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.org.serratec.biblioteca.entity.Emprestimo;

public interface EmprestimoRepository extends JpaRepository<Emprestimo, Integer>{
    
}
