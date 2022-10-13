package br.org.serratec.biblioteca.dto;

import br.org.serratec.biblioteca.entity.Editora;

public class EditoraDTO {
    private int codigoEditora;
    private String nome;

    public EditoraDTO(){
    
    }

    public EditoraDTO(int codigoEditora, String nome){
        this.codigoEditora = codigoEditora;
        this.nome = nome;
    }

    public int getCodigoEditora() {
        return codigoEditora;
    }

    public void setCodigoEditora(int codigoEditora) {
        this.codigoEditora = codigoEditora;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public EditoraDTO setAllAtributos(EditoraDTO editoraDTO){
        this.setCodigoEditora(editoraDTO.getCodigoEditora());
        this.setNome(editoraDTO.getNome());
        return this;
    }    

    public EditoraDTO setAllAtributos(Editora editora){
        this.setCodigoEditora(editora.getCodigoEditora());
        this.setNome(editora.getNome());
        return this;
    }    
    public Editora converterParaEntidade(EditoraDTO editoraDTO){
        Editora editora = new Editora();
        editora.setNome(editoraDTO.getNome());
        return editora;
    }   
}
