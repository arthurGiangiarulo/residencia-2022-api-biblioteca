package br.org.serratec.biblioteca.entity;

import java.time.Instant;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import br.org.serratec.biblioteca.dto.AlunoDTO;

@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "numeroMatriculaAluno")
@Entity
@Table(name = "alunos")
public class Aluno {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "numeromatriculaaluno")
    private int numeroMatriculaAluno;

    @Column(name = "nome")
    private String nome;

    @Column(name = "datanascimento")
    private Instant dataNascimento;

    @Column(name = "cpf")
    private String cpf;

    @Column(name = "logradouro")
    private String logradouro;

    @Column(name = "numerologradouro")
    private String numeroLogradouro;

    @Column(name = "complemento")
    private String complemento;

    @Column(name = "bairro")
    private String bairro;
    
    @Column(name = "cidade")
    private String cidade;

    // @JsonManagedReference(value="aluno-back")
    @OneToMany(mappedBy = "aluno")
    private Set<Emprestimo> emprestimosDoAluno;

    //Getters and Setters
    public int getNumeroMatriculaAluno() {
        return numeroMatriculaAluno;
    }
    public void setNumeroMatriculaAluno(int numeroMatriculaAluno) {
        this.numeroMatriculaAluno = numeroMatriculaAluno;
    }
    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }
    public Instant getDataNascimento() {
        return dataNascimento;
    }
    public void setDataNascimento(Instant dataNascimento) {
        this.dataNascimento = dataNascimento;
    }
    public String getCpf() {
        return cpf;
    }
    public void setCpf(String cpf) {
        this.cpf = cpf;
    }
    public String getLogradouro() {
        return logradouro;
    }
    public void setLogradouro(String logradouro) {
        this.logradouro = logradouro;
    }
    public String getNumeroLogradouro() {
        return numeroLogradouro;
    }
    public void setNumeroLogradouro(String numeroLogradouro) {
        this.numeroLogradouro = numeroLogradouro;
    }
    public String getComplemento() {
        return complemento;
    }
    public void setComplemento(String complemento) {
        this.complemento = complemento;
    }
    public String getBairro() {
        return bairro;
    }
    public void setBairro(String bairro) {
        this.bairro = bairro;
    }
    public String getCidade() {
        return cidade;
    }
    public void setCidade(String cidade) {
        this.cidade = cidade;
    }
    public Set<Emprestimo> getEmprestimosDoAluno() {
        return emprestimosDoAluno;
    }
    public void setEmprestimosDoAluno(Set<Emprestimo> emprestimosDoAluno) {
        this.emprestimosDoAluno = emprestimosDoAluno;
    }

    public Aluno setAllAtributos(Aluno aluno){
        this.setBairro(aluno.getBairro());
        this.setCidade(aluno.getCidade());
        this.setComplemento(aluno.getComplemento());
        this.setCpf(aluno.getCpf());
        this.setDataNascimento(aluno.getDataNascimento());
        this.setEmprestimosDoAluno(aluno.getEmprestimosDoAluno());
        this.setLogradouro(aluno.getLogradouro());
        this.setNome(aluno.getNome());
        this.setNumeroLogradouro(aluno.getNumeroLogradouro());
        return this;
    }

    public Aluno setAllAtributosFromDTO(AlunoDTO alunoDTO){
        this.setBairro(alunoDTO.getBairro());
        this.setCidade(alunoDTO.getCidade());
        this.setComplemento(alunoDTO.getComplemento());
        this.setCpf(alunoDTO.getCpf());
        this.setDataNascimento(alunoDTO.getDataNascimento());
        //this.emprestimosDoAluno(AlunoDTO.getEmprestimosDoAluno());
        this.setLogradouro(alunoDTO.getLogradouro());
        this.setNome(alunoDTO.getNome());
        this.setNumeroLogradouro(alunoDTO.getNumeroLogradouro());
        return this;
    }
}
