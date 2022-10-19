package br.org.serratec.biblioteca.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.org.serratec.biblioteca.repository.AlunoRepository;
import br.org.serratec.biblioteca.repository.EmprestimoRepository;
import br.org.serratec.biblioteca.dto.AlunoDTO;
import br.org.serratec.biblioteca.dto.EmprestimoDTO;
import br.org.serratec.biblioteca.entity.Aluno;
import br.org.serratec.biblioteca.entity.Emprestimo;

@Service
public class AlunoService {
    @Autowired
    AlunoRepository alunoRepository;
    @Autowired
    EmprestimoRepository emprestimoRepository;
    @Autowired
    EmprestimoService emprestimoService;

    public List<Aluno> getAllAlunos(){
        return alunoRepository.findAll();
    }

    public Aluno getAlunoById(int id){
        return alunoRepository.findById(id).orElse(null);
    }

    public List<AlunoDTO> getEmprestimosPorAlunosDTO(){
        List<Aluno> listaAlunos = getAllAlunos();
        List<AlunoDTO> listaAlunosDTO = new ArrayList<>();

        for(Aluno aluno: listaAlunos){
            List<Emprestimo> listaEmprestimos = emprestimoRepository.findByAluno(aluno);
            AlunoDTO alunoDTO = converteAlunoEntityToDTO(aluno);
            List<EmprestimoDTO> listaEmprestimosDTO= new ArrayList<>();

            for(Emprestimo emprestimo: listaEmprestimos){
                EmprestimoDTO emprestimoDTO = emprestimoService.converteEmprestimoEntityToDTO(emprestimo);
                listaEmprestimosDTO.add(emprestimoDTO);
            }
            alunoDTO.setEmprestimosDoAlunoDTO(listaEmprestimosDTO);
            listaAlunosDTO.add(alunoDTO);
        }
        return listaAlunosDTO;
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

    private AlunoDTO converteAlunoEntityToDTO(Aluno aluno) {
        AlunoDTO alunoDTO = new AlunoDTO();
        alunoDTO.setAllAtributosFromEntidade(aluno);
        return alunoDTO;
    }

    private Aluno converteAlunoDTOtoEntity(AlunoDTO alunoDTO) {
        Aluno aluno = new Aluno();
        aluno.setAllAtributosFromDTO(alunoDTO);
        return aluno;
    }
}
 