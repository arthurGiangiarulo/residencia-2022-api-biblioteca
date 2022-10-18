package br.org.serratec.biblioteca.dto;

import java.time.Instant;

import br.org.serratec.biblioteca.entity.Aluno;

public class AlunoDTO {
    private int numeroMatriculaAluno;
    private String nome;
    private Instant dataNascimento;
    private String cpf;
    private String logradouro;
    private String numeroLogradouro;
    private String complemento;
    private String bairro;
    private String cidade;
    // private Set<Emprestimo> emprestimosDoAluno;
    
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

    public AlunoDTO setAllAtributosFromEntidade(Aluno aluno){
        this.setNumeroMatriculaAluno(aluno.getNumeroMatriculaAluno());
        this.setNome(aluno.getNome());
        this.setDataNascimento(aluno.getDataNascimento());
        this.setCpf(aluno.getCpf());
        this.setLogradouro(aluno.getLogradouro());
        this.setNumeroLogradouro(aluno.getNumeroLogradouro());
        this.setComplemento(aluno.getComplemento());
        this.setBairro(aluno.getBairro());
        this.setCidade(aluno.getCidade());
        //this.emprestimosDoAluno(AlunoDTO.getEmprestimosDoAluno());
        return this;
    }
}
