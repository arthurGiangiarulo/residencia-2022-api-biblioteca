package br.org.serratec.biblioteca.dto;

import br.org.serratec.biblioteca.entity.Editora;

public class EditoraDTO {
    private int codigoEditora;
    private String nome;

    public EditoraDTO(){
    }

    public EditoraDTO(int codigoEditora, String nome){
        setCodigoEditora(codigoEditora);
        setNome(nome);
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

    public EditoraDTO setAllAtributosFromEntidade(Editora editora){
        this.setCodigoEditora(editora.getCodigoEditora());
        this.setNome(editora.getNome());
        return this;
    }
}
