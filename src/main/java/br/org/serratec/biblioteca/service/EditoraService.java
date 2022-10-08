package br.org.serratec.biblioteca.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.org.serratec.biblioteca.repository.EditoraRepository;
import br.org.serratec.biblioteca.entity.Editora;

@Service
public class EditoraService {
    @Autowired
    EditoraRepository editoraRepository;

    public List<Editora> getAllEditoras(){
        return editoraRepository.findAll();
    }

    public Editora getEditoraById(int id){
        return editoraRepository.findById(id).orElse(null);
    }

    public Editora saveEditora(Editora editora){
        return editoraRepository.save(editora); 
    }

    public Editora updateEditora(Editora editora, int id){
        Editora editoraExistenteNoBanco = getEditoraById(id);
        editoraExistenteNoBanco.setAllAtributos(editora);
        return editoraRepository.save(editoraExistenteNoBanco);
    }

    public Editora deleteEditora(int id){
        editoraRepository.deleteById(id);
        return getEditoraById(id);
    }
}
 
