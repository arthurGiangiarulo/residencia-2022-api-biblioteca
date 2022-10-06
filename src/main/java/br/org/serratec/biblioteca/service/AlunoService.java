package br.org.serratec.biblioteca.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.org.serratec.biblioteca.repository.AlunoRepository;
import br.org.serratec.biblioteca.entity.Aluno;

@Service
public class AlunoService {
    @Autowired
    AlunoRepository alunoRepository;

    public List<Aluno> getAllAlunos(){
        return alunoRepository.findAll();
    }

    public Aluno getAlunoById(int id){
        return alunoRepository.findById(id).get();
        //return alunoRepository.findById(id).orElse(null);
    }

    public Aluno saveAluno(Aluno aluno){
        return alunoRepository.save(aluno); 
    }

    public Aluno updateAluno(Aluno aluno, int id){
        Aluno alunoExistenteNoBanco = getAlunoById(id);
        alunoExistenteNoBanco.setAllAtributos(aluno);
        return alunoRepository.save(alunoExistenteNoBanco);
    }

    public Aluno deleteAluno(int id){
        alunoRepository.deleteById(id);
        return getAlunoById(id);
    }
}
 