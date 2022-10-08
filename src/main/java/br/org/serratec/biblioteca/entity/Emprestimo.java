package br.org.serratec.biblioteca.entity;

import java.math.BigDecimal;
import java.time.Instant;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity 
@Table(name = "editora")
public class Emprestimo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "codigoemprestimo")
    private int codigoEmprestimo;
    
    @Column(name = "dataemprestimo")
    private Instant dataEmprestimo;
    
    @Column(name = "dataentrega")
    private Instant dataEntrega;
    
    @Column(name = "valoremprestimo")
    private BigDecimal valorEmprestimo;

    @ManyToOne
    @JoinColumn(name = "numeromatriculaaluno", referencedColumnName = "numeromatriculaaluno")
    private Aluno aluno;
    
    @ManyToOne
    @JoinColumn(name = "codigolivro", referencedColumnName = "codigolivro")
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

    public Emprestimo setAllAtributos(Emprestimo emprestimo){
        this.setDataEmprestimo(emprestimo.getDataEmprestimo());
        this.setDataEntrega(emprestimo.getDataEntrega());
        this.setValorEmprestimo(emprestimo.getValorEmprestimo());
        this.setAluno(emprestimo.getAluno());
        this.setLivro(emprestimo.getLivro());
        return this;
    }
}
