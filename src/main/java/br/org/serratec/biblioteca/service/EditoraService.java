package br.org.serratec.biblioteca.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PutMapping;

import br.org.serratec.biblioteca.repository.EditoraRepository;
import br.org.serratec.biblioteca.dto.EditoraDTO;
import br.org.serratec.biblioteca.entity.Editora;

@Service
public class EditoraService {
    @Autowired
    EditoraRepository editoraRepository;

    public List<Editora> getAllEditoras(){
        return editoraRepository.findAll();
    }

    // public List<EditoraDTO> getAllEditorasDTO(){
    //     List<EditoraDTO> listaDTO = new ArrayList<>();
    //     BeanUtils.copyProperties((editoraRepository.findAll()), listaDTO);
    //     return listaDTO;
    // }

    public List<EditoraDTO> getAllEditorasDTO() {
        List<Editora> editora = new ArrayList<Editora>();
        List<EditoraDTO> editoraDTO = new ArrayList<EditoraDTO>();
        editora = getAllEditoras();
        editora.forEach(edt -> {
            editoraDTO.add(converteEditoraEntityToDTO(edt));
        });
        return editoraDTO;
    }

    public Editora getEditoraById(int id){
        return editoraRepository.findById(id).orElse(null);
    }

    public EditoraDTO getEditoraDTOById(int id) {
        Editora editora = editoraRepository.findById(id).orElse(null);
        if(editora != null) {
            return converteEditoraEntityToDTO(editora);
        } else {
            return null;
        }
    }

    public Editora saveEditora(Editora editora){
        return editoraRepository.save(editora); 
    }

    public EditoraDTO saveEditoraDTO(EditoraDTO editoraDTO){
        return converteEditoraEntityToDTO(editoraRepository.save(converteEditoraDTOtoEntity(editoraDTO))); 
    }

    public Editora updateEditora(Editora editora, int id){
        Editora editoraExistenteNoBanco = getEditoraById(id);
        return editoraRepository.save(editoraExistenteNoBanco.setAllAtributos(editora));
    }

    @PutMapping("/dto/{id}")
    public EditoraDTO updateEditoraDTO(EditoraDTO editoraDTO, int id){
        if(getEditoraById(id) != null){
            return converteEditoraEntityToDTO(editoraRepository.save(converteEditoraDTOtoEntity(editoraDTO)));
        } return null;
    //Corrigir a brecha que há quando o usuário preenche o id do JSON diferente do id do http. 
    }

    public Editora deleteEditora(int id){
        editoraRepository.deleteById(id);
        return getEditoraById(id);
    }
    
    private EditoraDTO converteEditoraEntityToDTO(Editora editora) {
        EditoraDTO editoraDTO = new EditoraDTO();
        editoraDTO.setAllAtributosFromEntidade(editora);
        return editoraDTO;
    }

    private Editora converteEditoraDTOtoEntity(EditoraDTO editoraDTO) {
        Editora editora = new Editora();
        editora.setAllAtributosFromDTO(editoraDTO);
        return editora;
    }
}
 
