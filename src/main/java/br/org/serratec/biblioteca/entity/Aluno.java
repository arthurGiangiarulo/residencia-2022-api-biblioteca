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

    @OneToMany(mappedBy = "aluno")
    private Set<Emprestimo> emprestimos;
}
