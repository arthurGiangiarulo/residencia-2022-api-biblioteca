package br.org.serratec.biblioteca.service;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
            editoraDTO.add(converteEntitytoDTO(edt));
        });
        return editoraDTO;
    }

    public Editora getEditoraById(int id){
        return editoraRepository.findById(id).orElse(null);
    }

    public EditoraDTO getEditoraDTOById(int id) {
        Editora editora = editoraRepository.findById(id).orElse(null);
        if(editora != null) {
            return converteEntitytoDTO(editora);
        } else {
            return null;
        }
    }

    public Editora saveEditora(Editora editora){
        return editoraRepository.save(editora); 
    }

    public EditoraDTO saveEditoraDTO(EditoraDTO editoraDTO){
        Editora editora = new Editora();
        editora.setAllAtributos(editoraDTO);
        
        Editora novaEditora = editoraRepository.save(editora); 
        EditoraDTO novaEditoraDTO = new EditoraDTO();

        novaEditoraDTO.setAllAtributos(novaEditora);
        return novaEditoraDTO;
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

    @Autowired
    private ModelMapper modelMapper;
    
    private EditoraDTO converteEntitytoDTO(Editora editora) {
        EditoraDTO editoraDTO = new EditoraDTO();
        editoraDTO = (modelMapper.map(editora, EditoraDTO.class));
        return editoraDTO;
    }
}
 
