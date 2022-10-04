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
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity 
@Table(name = "editora")
public class Emprestimo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "codigoemprestimo")
    private int codigoemprestimo;
    
    @Column(name = "dataemprestimo")
    private Instant dataEmprestimo;
    
    @Column(name = "dataentrega")
    private Instant dataEntrega;
    
    @Column(name = "valoremprestimo")
    private BigDecimal valorEmprestimo;

    @ManyToOne
    @JoinColumn(name = "numeromatriculaaluno", referencedColumnName = "numeromatriculaaluno")
    private Aluno aluno;
    
    @OneToOne
    @JoinColumn(name = "codigolivro", referencedColumnName = "codigolivro")
    private Livro livro;
}
