package br.org.serratec.biblioteca.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.org.serratec.biblioteca.repository.EmprestimoRepository;
import br.org.serratec.biblioteca.entity.Emprestimo;

@Service
public class EmprestimoService {
    @Autowired
    EmprestimoRepository emprestimoRepository;

    public List<Emprestimo> getAllEmprestimos(){
        return emprestimoRepository.findAll();
    }

    public Emprestimo getEmprestimoById(int id){
        return emprestimoRepository.findById(id).get();
        //return emprestimoRepository.findById(id).orElse(null);
    }

    public Emprestimo saveEmprestimo(Emprestimo emprestimo){
        return emprestimoRepository.save(emprestimo); 
    }

    public Emprestimo updateEmprestimo(Emprestimo emprestimo, int id){
        Emprestimo emprestimoExistenteNoBanco = getEmprestimoById(id);
        emprestimoExistenteNoBanco.setAllAtributos(emprestimo);
        return emprestimoRepository.save(emprestimoExistenteNoBanco);
    }

    public Emprestimo deleteEmprestimo(int id){
        emprestimoRepository.deleteById(id);
        return getEmprestimoById(id);
    }
}
