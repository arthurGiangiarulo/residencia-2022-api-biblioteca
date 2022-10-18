package br.org.serratec.biblioteca.dto;

import java.time.Instant;

import br.org.serratec.biblioteca.entity.Livro;

public class LivroDTO {
    private int codigoLivro;
    private String nomeLivro;
    private String nomeAutor;
    private Instant dataLancamento;
    private int codigoIsbn;
    private EditoraDTO editoraDTO;
    // private Emprestimo emprestimosDoLivro;
    
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
    
    public EditoraDTO getEditoraDTO() {
        return editoraDTO;
    }
    
    public void setEditoraDTO(EditoraDTO editoraDTO) {
        this.editoraDTO = editoraDTO;
    }
    
    // public EmprestimoDTO getEmprestimosDoLivroDTO() {
    //     return emprestimosDoLivroDTO;
    // }
    
    // public void setEmprestimosDoLivroDTO(EmprestimoDTO emprestimosDoLivroDTO) {
    //     this.emprestimosDoLivroDTO = emprestimosDoLivroDTO;
    // }
    
    public LivroDTO setAllAtributosFromEntidade(Livro livro){
        this.setCodigoLivro(livro.getCodigoLivro());
        this.setNomeLivro(livro.getNomeLivro());
        this.setNomeAutor(livro.getNomeAutor());
        this.setDataLancamento(livro.getDataLancamento());
        this.setCodigoIsbn(livro.getCodigoIsbn());
        // this.setEditoraDTO(livro.getEditora());
        // this.setEmprestimosDoLivroDTO(livro.getEmprestimoDoLivro());
        return this;
    }
}
