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
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import br.org.serratec.biblioteca.dto.LivroDTO;

@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "codigoLivro")
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

    // @JsonBackReference(value="editora-back")
    @ManyToOne
    @JoinColumn(name = "codigoeditora", referencedColumnName = "codigoeditora")
    private Editora editora;

    // @JsonManagedReference(value="livro-back")
    @OneToMany(mappedBy = "livro")
    private Set<Emprestimo> emprestimoDoLivro;

    
    public int getCodigoLivro() {
        return codigoLivro;
    }
    
    public void setCodigoLivro(int codigoLivro) {
        this.codigoLivro = codigoLivro;
    }
    
    public String getNomeLivro() {
        return nomeLivro;
    }
    
    public void setNomeLivro(String nomeLivro) {
        this.nomeLivro = nomeLivro;
    }
    
    public String getNomeAutor() {
        return nomeAutor;
    }
    
    public void setNomeAutor(String nomeAutor) {
        this.nomeAutor = nomeAutor;
    }
    
    public Instant getDataLancamento() {
        return dataLancamento;
    }
    
    public void setDataLancamento(Instant dataLancamento) {
        this.dataLancamento = dataLancamento;
    }
    
    public int getCodigoIsbn() {
        return codigoIsbn;
    }
    
    public void setCodigoIsbn(int codigoIsbn) {
        this.codigoIsbn = codigoIsbn;
    }
    
    public Editora getEditora() {
        return editora;
    }
    
    public void setEditora(Editora editora) {
        this.editora = editora;
    }
    
    public Set<Emprestimo> getEmprestimoDoLivro() {
        return emprestimoDoLivro;
    }

    public void setEmprestimoDoLivro(Set<Emprestimo> emprestimoDoLivro) {
        this.emprestimoDoLivro = emprestimoDoLivro;
    }

    public Livro setAllAtributos(Livro livro){
        this.setCodigoIsbn(livro.getCodigoIsbn());
        this.setDataLancamento(livro.getDataLancamento());
        this.setEditora(livro.getEditora());
        this.setEmprestimoDoLivro(livro.getEmprestimoDoLivro());
        this.setNomeAutor(livro.getNomeAutor());
        this.setNomeLivro(livro.getNomeLivro());
        return this;
    }

    public Livro setAllAtributosFromDTO (LivroDTO livroDTO){
        this.setNomeLivro(livroDTO.getNomeLivro());
        this.setNomeAutor(livroDTO.getNomeAutor());
        this.setDataLancamento(livroDTO.getDataLancamento());
        this.setCodigoIsbn(livroDTO.getCodigoIsbn());
        // this.setEditora(livroDTO.getEditora());
        // this.setEmprestimoDoLivro(livroDTO.getEmprestimosDoLivro());
        return null; 
    }
}
