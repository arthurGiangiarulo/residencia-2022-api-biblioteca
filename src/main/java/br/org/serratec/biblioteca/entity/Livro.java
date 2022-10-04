package br.org.serratec.biblioteca.entity;

import java.time.Instant;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "livros")
public class Livro {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "codigolivro")
    private int codigoLivro;

    @Column(name = "nomelivro")
    private String nomeLivro;

    @Column(name = "nomeautor")
    private String nomeAutor;

    @Column(name = "datalancamento")
    private Instant dataLancamento;

    @Column(name = "codigoisbn")
    private int codigoIsbn;

    @ManyToOne
    @JoinColumn(name = "codigoeditora", referencedColumnName = "codigoeditora")
    private Editora editora;

    @OneToOne(mappedBy = "livro")
    private Emprestimo emprestimoDoLivro;
}
