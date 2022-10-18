package br.org.serratec.biblioteca.dto;

import java.math.BigDecimal;
import java.time.Instant;

import br.org.serratec.biblioteca.entity.Aluno;
import br.org.serratec.biblioteca.entity.Livro;

public class EmprestimoDTO {
    private int codigoEmprestimo;
    private Instant dataEmprestimo;
    private Instant dataEntrega;
    private BigDecimal valorEmprestimo;
    private Aluno aluno;
    private Livro livro;
    
    public int getCodigoEmprestimo() {
        return codigoEmprestimo;
    }
    public void setCodigoEmprestimo(int codigoEmprestimo) {
        this.codigoEmprestimo = codigoEmprestimo;
    }
    public Instant getDataEmprestimo() {
        return dataEmprestimo;
    }
    public void setDataEmprestimo(Instant dataEmprestimo) {
        this.dataEmprestimo = dataEmprestimo;
    }
    public Instant getDataEntrega() {
        return dataEntrega;
    }
    public void setDataEntrega(Instant dataEntrega) {
        this.dataEntrega = dataEntrega;
    }
    public BigDecimal getValorEmprestimo() {
        return valorEmprestimo;
    }
    public void setValorEmprestimo(BigDecimal valorEmprestimo) {
        this.valorEmprestimo = valorEmprestimo;
    }
    public Aluno getAluno() {
        return aluno;
    }
    public void setAluno(Aluno aluno) {
        this.aluno = aluno;
    }
    public Livro getLivro() {
        return livro;
    }
    public void setLivro(Livro livro) {
        this.livro = livro;
    }
}
